package com.nhnacademy.townoffice.service.resident;

import com.nhnacademy.townoffice.domain.dto.ResidentFamilyDto;
import com.nhnacademy.townoffice.domain.dto.ResidentListDto;
import com.nhnacademy.townoffice.domain.forView.TransForResident;
import com.nhnacademy.townoffice.domain.enumType.FamilyType;
import com.nhnacademy.townoffice.domain.request.ResidentRegister;
import com.nhnacademy.townoffice.entity.Resident;
import com.nhnacademy.townoffice.exception.ExistFamilyException;
import com.nhnacademy.townoffice.repository.resident.ResidentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ResidentServiceImpl implements ResidentService {
    private final ResidentRepository residentRepository;
    public ResidentServiceImpl(ResidentRepository residentRepository){
        this.residentRepository = residentRepository;
    }

    public List<Resident> getResidentList(){
        return residentRepository.findAll();
    }

    public Resident saveResident(ResidentRegister residentRegister){
        Resident resident= Resident.builder()
                .residentSerialNumber(null)
                .name(residentRegister.getName())
                .residentRegistrationNumber(residentRegister.getResidentRegistrationNumber())
                .genderCode(residentRegister.getGenderCode())
                .birthDate(residentRegister.getBirthDate())
                .birthPlaceCode(residentRegister.getBirthPlaceCode())
                .registrationBaseAddress(residentRegister.getRegistrationBaseAddress())
                .build();
        return residentRepository.save(resident);
    }

    public Resident modifyResident(Resident resident){
        return residentRepository.save(resident);
    }

    public Resident getResident(Integer serialNumber){
        return residentRepository.findResidentByResidentSerialNumber(serialNumber);
    }

    @Transactional(readOnly = true)
    public Page<ResidentListDto> findResidentList(Pageable pageable){
        return residentRepository.findResidents(pageable);
    }
    @Transactional
    public List<TransForResident> getResidentDtos(Integer residentSerialNum){
       List<ResidentFamilyDto> residentFamilyDtos =  residentRepository.findResidentDtoByResidentRegistrationNumber(residentSerialNum);
       List<TransForResident> translateResidentDtos = new ArrayList<>();
       Resident resident = residentRepository.findResidentByResidentSerialNumber(residentSerialNum);
       String date1 = resident.getBirthDate().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"));
       translateResidentDtos.add(new TransForResident(FamilyType.본인, resident.getName(), date1, resident.getResidentRegistrationNumber(), resident.getGenderCode()));

       for(ResidentFamilyDto residentFamilyDto : residentFamilyDtos){
           String date = residentFamilyDto.getBirthDate().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"));
           translateResidentDtos.add(new TransForResident(residentFamilyDto.getFamilyRelationshipCode(),
                   residentFamilyDto.getName(),
                   date,
                   residentFamilyDto.getResidentRegistrationNumber(),
                   residentFamilyDto.getGenderCode()));
       }
       return translateResidentDtos;
    }
    @Transactional
    public void deleteResident(Integer serialNumber){
        if(residentRepository.existsResidentFindByHousehold(serialNumber)>1){
            throw new ExistFamilyException("세대원이 남아있어서 삭제가 불가능합니다.");
        }
        residentRepository.deleteById(serialNumber);
    }

}
