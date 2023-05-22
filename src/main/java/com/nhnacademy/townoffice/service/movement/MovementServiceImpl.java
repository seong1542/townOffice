package com.nhnacademy.townoffice.service.movement;

import com.nhnacademy.townoffice.domain.dto.HouseholdDto;
import com.nhnacademy.townoffice.domain.enumType.AnswerType;
import com.nhnacademy.townoffice.domain.forView.TransForMovement;
import com.nhnacademy.townoffice.domain.request.HouseAddressModify;
import com.nhnacademy.townoffice.domain.request.HouseAddressRegister;
import com.nhnacademy.townoffice.entity.Household;
import com.nhnacademy.townoffice.entity.HouseholdMovementAddress;
import com.nhnacademy.townoffice.exception.SomethingNotFoundException;
import com.nhnacademy.townoffice.repository.composition.CompositionRepository;
import com.nhnacademy.townoffice.repository.houseMove.HouseholdMovementRepository;
import com.nhnacademy.townoffice.repository.HouseholdRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class MovementServiceImpl implements MovementService {
    private final HouseholdMovementRepository movementRepository;
    private final HouseholdRepository householdRepository;
    private final CompositionRepository compositionRepository;


    public HouseholdMovementAddress saveMovementAddress(Integer householdNumber, HouseAddressRegister houseAddressRegister){
        if(!householdRepository.existsById(householdNumber)){
            throw new SomethingNotFoundException("세대 일련번호가 없습니다.");
        }
        Household household = householdRepository.findById(householdNumber).orElseThrow();

        HouseholdMovementAddress movementAddress = new HouseholdMovementAddress(new HouseholdMovementAddress.Pk(householdNumber, houseAddressRegister.getHouseMovementDate()),
                household,
                houseAddressRegister.getHouseMovementAddress(),
                AnswerType.Y);

        HouseholdMovementAddress householdMovementAddress = movementRepository.findByHouseholdNumberAndLastAddress(householdNumber);
        if(Objects.nonNull(householdMovementAddress)){
            householdMovementAddress.setLastAddress(AnswerType.N);
            movementRepository.save(householdMovementAddress);
        }
        household.setCurrentHouseAddress(houseAddressRegister.getHouseMovementAddress());
        householdRepository.save(household);
        return movementRepository.save(movementAddress);
    }


    public void modifyMovementAddress(Integer householdNumber, LocalDate date, HouseAddressModify houseAddressModify){
         movementRepository.updateHouseMovementAddress(householdNumber,date, houseAddressModify.getHouseMovementAddress());
    }


    public void deleteMovementAddress(Integer householdNumber, LocalDate date){
        if(!movementRepository.existsById(new HouseholdMovementAddress.Pk(householdNumber,date))){
            throw new SomethingNotFoundException("전입신고가 존재하지 않습니다.");
        }
        Household household = householdRepository.findById(householdNumber).orElseThrow();
        HouseholdMovementAddress.Pk pk = new HouseholdMovementAddress.Pk(householdNumber, date);
        movementRepository.deleteById(pk);
        if(movementRepository.getHouseholdMovementDtoById(householdNumber).size()>0){
            HouseholdMovementAddress oldMovement = movementRepository.findByHouseholdNumberAndLastAddress(householdNumber);
            oldMovement.setLastAddress(AnswerType.Y);
            household.setCurrentHouseAddress(oldMovement.getHouseMovementAddress());
            householdRepository.save(household);
            movementRepository.save(oldMovement);
        }
    }

    public List<TransForMovement> getMovementList(Integer serialNumber){
        HouseholdDto householdDto = compositionRepository.getHouseholdDto(serialNumber);
        List<HouseholdMovementAddress> householdMovementAddressList = movementRepository.findAllByHousehold_HouseholdSerialNumberOrderByPk_HouseMovementDateDesc(householdDto.getHouseholdSerialNumber());
        List<TransForMovement> transForMovementList=new ArrayList<>();
        for(HouseholdMovementAddress movementAddress : householdMovementAddressList){
            String statusAddress = "";
            if(movementAddress.getLastAddress().equals(AnswerType.Y)){
                statusAddress = "현주소";
            }
            else {
                statusAddress = "전주소";
            }
            transForMovementList.add(new TransForMovement(movementAddress.getPk().getHouseMovementDate(), movementAddress.getHouseMovementAddress(),statusAddress));
        }
        return transForMovementList;
    }

}
