package com.nhnacademy.townoffice.service.composition;

import com.nhnacademy.townoffice.domain.dto.AddressDto;
import com.nhnacademy.townoffice.domain.dto.HouseholdCompositionResidentDto;
import com.nhnacademy.townoffice.domain.dto.HouseholdDto;
import com.nhnacademy.townoffice.domain.forView.TransForHousehold;
import com.nhnacademy.townoffice.entity.Resident;
import com.nhnacademy.townoffice.exception.SomethingNotFoundException;
import com.nhnacademy.townoffice.repository.composition.CompositionRepository;
import com.nhnacademy.townoffice.repository.resident.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompositionServiceImpl implements CompositionService{
    private final CompositionRepository compositionRepository;
    private final ResidentRepository residentRepository;

    public AddressDto getAddressBySerialNumber(Integer serialNumber){
        AddressDto addressDto = compositionRepository.getCurrentAddress(serialNumber);
        if(Objects.isNull(addressDto)){
            throw new SomethingNotFoundException("구성원으로 등록되어있지 않습니다.");
        }
        return addressDto;
    }

    @Transactional
    public TransForHousehold getHouseholdBySerialNumber(Integer serialNumber){
        HouseholdDto householdDto= compositionRepository.getHouseholdDto(serialNumber);
        Resident resident = residentRepository.findResidentByResidentSerialNumber(householdDto.getResidentSerialNumber());
        String reasonAndDate = householdDto.getHouseholdReasonCode()+" "+householdDto.getHouseholdCompositionDate().toString();
        return new TransForHousehold(resident.getName(),reasonAndDate);
    }

    @Transactional
    public List<HouseholdCompositionResidentDto> getCompositionList(Integer serialNumber){
        HouseholdDto householdDto = compositionRepository.getHouseholdDto(serialNumber);
        return compositionRepository.getAllWithAssociations(householdDto.getHouseholdSerialNumber());
    }

}
