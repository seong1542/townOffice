package com.nhnacademy.townoffice.service.family;

import com.nhnacademy.townoffice.domain.dto.ParentsDto;
import com.nhnacademy.townoffice.domain.enumType.FamilyType;
import com.nhnacademy.townoffice.domain.request.FamilyModify;
import com.nhnacademy.townoffice.domain.request.FamilyRegister;
import com.nhnacademy.townoffice.entity.FamilyRelationship;

import java.util.List;

public interface FamilyRelationshipService {
    FamilyRelationship saveFamilyRelationship(Integer base, FamilyRegister register);
    FamilyRelationship modifyFamilyRelationship(FamilyRelationship familyRelationship, FamilyModify familyModify);
    void removeFamily(Integer base, Integer familyNum);
    FamilyRelationship getFamilyRelationship(Integer base, Integer familySerialNumber);
    ParentsDto getParent(Integer target, FamilyType type);
    List<FamilyRelationship> getAllFamilyRelationshipByBase(Integer baseSerialNumber);
}
