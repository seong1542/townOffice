package com.nhnacademy.townoffice.domain.dto;

import com.nhnacademy.townoffice.domain.enumType.FamilyType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
public class HouseholdCompositionResidentDto{
    private FamilyType householdRelationshipCode;
    private String name;
    private String residentRegistrationNumber;
    private LocalDate reportDate;
    private String changeReason;

    @QueryProjection
    public HouseholdCompositionResidentDto(FamilyType householdRelationshipCode, String name, String residentRegistrationNumber, LocalDate reportDate, String changeReason){
        this.householdRelationshipCode = householdRelationshipCode;
        this.name = name;
        this.residentRegistrationNumber = residentRegistrationNumber;
        this.reportDate = reportDate;
        this.changeReason = changeReason;
    }

}
