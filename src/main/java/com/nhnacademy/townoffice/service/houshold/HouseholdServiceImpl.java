package com.nhnacademy.townoffice.service.houshold;

import com.nhnacademy.townoffice.domain.request.HouseholdRegister;
import com.nhnacademy.townoffice.entity.Household;
import com.nhnacademy.townoffice.entity.Resident;
import com.nhnacademy.townoffice.exception.SomethingNotFoundException;
import com.nhnacademy.townoffice.repository.HouseholdRepository;
import com.nhnacademy.townoffice.repository.resident.ResidentRepository;
import com.nhnacademy.townoffice.service.houshold.HouseholdService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class HouseholdServiceImpl implements HouseholdService {
    private final HouseholdRepository householdRepository;
    private final ResidentRepository residentRepository;

    public Household saveHousehold(HouseholdRegister householdRegister){
        Resident resident = residentRepository.findResidentByResidentSerialNumber(householdRegister.getHouseholdResident());
        if(Objects.isNull(resident)){
            throw new SomethingNotFoundException("해당 주민이 존재하지 않습니다.");
        }
        Household household = new Household(null,
                resident,
                householdRegister.getHouseholdCompositionDate(),
                householdRegister.getHouseholdReasonCode(),
                householdRegister.getCurrentHouseAddress(),
                new ArrayList<>(), new ArrayList<>()); // movement-residents 순
        return householdRepository.save(household);
    }

    public List<Household> getHouseholds(){
        return householdRepository.findAll();
    }

    public void deleteHousehold(Integer houseNum){
        if(!householdRepository.existsById(houseNum)){
            throw new SomethingNotFoundException("세대가 존재하지 않습니다.");
        }

        householdRepository.deleteById(houseNum);
    }

}
