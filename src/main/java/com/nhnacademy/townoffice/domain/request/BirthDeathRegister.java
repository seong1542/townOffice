package com.nhnacademy.townoffice.domain.request;

import com.nhnacademy.townoffice.domain.enumType.BirthDeathCode;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BirthDeathRegister {
    private Integer residentSerialNumber;
    private BirthDeathCode birthDeathTypeCode;
    private LocalDate reportDate;
    private String birthReporterCode;
    private String deathReporterCode;
    private String email;
    private String phoneNumber;
    private String deathPlace;
    private String deathAddress;
}
