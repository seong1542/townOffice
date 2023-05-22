package com.nhnacademy.townoffice.service.family;

import com.nhnacademy.townoffice.domain.dto.ParentsDto;
import com.nhnacademy.townoffice.domain.enumType.FamilyType;
import com.nhnacademy.townoffice.domain.request.FamilyModify;
import com.nhnacademy.townoffice.domain.request.FamilyRegister;
import com.nhnacademy.townoffice.entity.FamilyRelationship;
import com.nhnacademy.townoffice.entity.Resident;
import com.nhnacademy.townoffice.exception.SomethingNotFoundException;
import com.nhnacademy.townoffice.repository.family.FamilyRelationshipRepository;
import com.nhnacademy.townoffice.repository.resident.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class FamilyRelationshipServiceImpl implements FamilyRelationshipService {
    private final FamilyRelationshipRepository familyRelationshipRepository;
    private final ResidentRepository residentRepository;

    public FamilyRelationship getFamilyRelationship(Integer base, Integer familySerialNumber){
        FamilyRelationship.Pk pk = new FamilyRelationship.Pk(familySerialNumber,base);
        return familyRelationshipRepository.findById(pk).orElseThrow(); // null값이 올경우 throw
    }

    public FamilyRelationship saveFamilyRelationship(Integer base, FamilyRegister register){
        Optional<Resident> resident = residentRepository.findById(base);
        Optional<Resident> familyResident = residentRepository.findById(register.getFamilyResidentSerialNumber());
        if(resident.isEmpty() || familyResident.isEmpty()){
            throw new SomethingNotFoundException("존재하는 주민이 없습니다.");
        }

        FamilyRelationship familyRelationship =
                new FamilyRelationship(new FamilyRelationship.Pk(register.getFamilyResidentSerialNumber(), base),
                        resident.get(),
                        familyResident.get(),
                        register.getFamilyRelationshipCode());
        return familyRelationshipRepository.save(familyRelationship);
    }

    public FamilyRelationship modifyFamilyRelationship(FamilyRelationship familyRelationship, FamilyModify familyModify){
        familyRelationship.setFamilyRelationshipCode(familyModify.getFamilyRelationshipCode());
        return familyRelationshipRepository.save(familyRelationship);
    }

    public void removeFamily(Integer base, Integer familyNum){
        FamilyRelationship.Pk pk = new FamilyRelationship.Pk(familyNum,base);

        familyRelationshipRepository.deleteById(pk);
    }

    public ParentsDto getParent(Integer target, FamilyType type){
        ParentsDto parentsDto = familyRelationshipRepository.getBySerialNumberAndFamilyCode(target, type);
        return parentsDto;
    }

    public List<FamilyRelationship> getAllFamilyRelationshipByBase(Integer baseSerialNumber){
        return familyRelationshipRepository.findAllByPk_BaseResidentSerialNumber(baseSerialNumber);
    }
}
