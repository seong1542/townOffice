package com.nhnacademy.townoffice.domain.dto;

import com.nhnacademy.townoffice.domain.enumType.FamilyType;
import com.nhnacademy.townoffice.domain.enumType.Gender;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ResidentFamilyDto {
    private FamilyType familyRelationshipCode;
    private String name;
    private LocalDateTime birthDate;
    private String residentRegistrationNumber;
    private Gender genderCode;

    @QueryProjection
    public ResidentFamilyDto(FamilyType familyRelationshipCode, String name, LocalDateTime birthDate, String residentRegistrationNumber, Gender genderCode ){
        this.familyRelationshipCode = familyRelationshipCode;
        this.name = name;
        this.birthDate = birthDate;
        this.residentRegistrationNumber = residentRegistrationNumber;
        this.genderCode = genderCode;
    }
}
