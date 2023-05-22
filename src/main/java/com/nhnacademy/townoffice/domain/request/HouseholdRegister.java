package com.nhnacademy.townoffice.domain.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class HouseholdRegister {
    private Integer householdResident;
    private LocalDate householdCompositionDate;
    private String householdReasonCode;
    private String currentHouseAddress;
}
