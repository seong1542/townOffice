package com.nhnacademy.townoffice.domain.dto;

import com.nhnacademy.townoffice.domain.enumType.FamilyType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ParentsDto {
    private Integer familyResidentSerialNumber;
    private String name;
    private FamilyType familyTypeCode;
    private String residentRegistrationNumber;
    @QueryProjection
    public ParentsDto(Integer familyResidentSerialNumber, String name, FamilyType familyTypeCode, String residentRegistrationNumber){
        this.familyResidentSerialNumber= familyResidentSerialNumber;
        this.name = name;
        this.familyTypeCode = familyTypeCode;
        this.residentRegistrationNumber = residentRegistrationNumber;
    }
}
