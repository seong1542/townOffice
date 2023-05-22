package com.nhnacademy.townoffice.repository.family;

import com.nhnacademy.townoffice.domain.dto.ParentsDto;
import com.nhnacademy.townoffice.domain.dto.QParentsDto;
import com.nhnacademy.townoffice.domain.enumType.FamilyType;
import com.nhnacademy.townoffice.entity.FamilyRelationship;
import com.nhnacademy.townoffice.entity.QFamilyRelationship;
import com.nhnacademy.townoffice.entity.QResident;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.Optional;

public class FamilyRelationshipRepositoryImpl extends QuerydslRepositorySupport implements FamilyRelationshipRepositoryCustom{
    public FamilyRelationshipRepositoryImpl(){
        super(FamilyRelationship.class);
    }

    @Override
    public ParentsDto getBySerialNumberAndFamilyCode(Integer target, FamilyType familyType) {
        QFamilyRelationship familyRelationship = QFamilyRelationship.familyRelationship;
        QResident resident = QResident.resident;

        return from(familyRelationship)
                .leftJoin(resident)
                .on(familyRelationship.pk.familyResidentSerialNumber.eq(resident.residentSerialNumber))
                .where(familyRelationship.pk.baseResidentSerialNumber.eq(target).and(familyRelationship.familyRelationshipCode.eq(familyType)))
                .select(new QParentsDto(familyRelationship.pk.familyResidentSerialNumber, resident.name, familyRelationship.familyRelationshipCode,resident.residentRegistrationNumber))
                .fetchOne();
    }
}
