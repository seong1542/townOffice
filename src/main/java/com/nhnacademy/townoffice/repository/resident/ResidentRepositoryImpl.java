package com.nhnacademy.townoffice.repository.resident;

import com.nhnacademy.townoffice.domain.dto.QResidentFamilyDto;
import com.nhnacademy.townoffice.domain.dto.QResidentListDto;
import com.nhnacademy.townoffice.domain.dto.ResidentFamilyDto;
import com.nhnacademy.townoffice.domain.dto.ResidentListDto;
import com.nhnacademy.townoffice.domain.enumType.BirthDeathCode;
import com.nhnacademy.townoffice.entity.QBirthDeathReportResident;
import com.nhnacademy.townoffice.entity.QFamilyRelationship;
import com.nhnacademy.townoffice.entity.QResident;
import com.nhnacademy.townoffice.entity.Resident;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ResidentRepositoryImpl extends QuerydslRepositorySupport implements ResidentRepositoryCustom {
    public ResidentRepositoryImpl(){
        super(Resident.class);
    }

    @Override
    public Page<ResidentListDto> findResidents(Pageable pageable) {
        QResident resident = QResident.resident;
        QBirthDeathReportResident birthDeathReportResident = QBirthDeathReportResident.birthDeathReportResident;

        Long count = from(resident)
                .select(resident.count())
                .fetchOne();

        List<ResidentListDto> residentListDtos = from(resident)
                .leftJoin(birthDeathReportResident)
                .on(birthDeathReportResident.pk.residentSerialNumber.eq(resident.residentSerialNumber))
                .select(new QResidentListDto(resident.residentSerialNumber,
                        resident.name,
                        birthDeathReportResident.pk.birthDeathTypeCode.eq(BirthDeathCode.출생),
                        resident.deathDate.isNotNull()))
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();
        return new PageImpl<>(residentListDtos, pageable, count);
    }

    @Override
    public List<ResidentFamilyDto> findResidentDtoByResidentRegistrationNumber(Integer residentSerialNum) {
        QResident resident = QResident.resident;
        QFamilyRelationship familyRelationship = QFamilyRelationship.familyRelationship;

        return from(resident)
                .leftJoin(familyRelationship)
                .on(resident.residentSerialNumber.eq(familyRelationship.pk.familyResidentSerialNumber))
                .where(familyRelationship.pk.baseResidentSerialNumber.eq(residentSerialNum))
                .select(new QResidentFamilyDto(familyRelationship.familyRelationshipCode,
                        resident.name,
                        resident.birthDate,
                        resident.residentRegistrationNumber,
                        resident.genderCode))
                .fetch();
    }
}
