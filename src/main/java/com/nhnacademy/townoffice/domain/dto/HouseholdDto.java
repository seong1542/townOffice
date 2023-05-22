package com.nhnacademy.townoffice.domain.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class HouseholdDto {
    private Integer householdSerialNumber;
    private Integer residentSerialNumber;
    private String householdReasonCode;
    private LocalDate householdCompositionDate;

    @QueryProjection
    public HouseholdDto(Integer householdSerialNumber, Integer residentSerialNumber, String householdReasonCode, LocalDate householdCompositionDate){
        this.householdSerialNumber = householdSerialNumber;
        this.residentSerialNumber = residentSerialNumber;
        this.householdReasonCode = householdReasonCode;
        this.householdCompositionDate = householdCompositionDate;
    }
}
