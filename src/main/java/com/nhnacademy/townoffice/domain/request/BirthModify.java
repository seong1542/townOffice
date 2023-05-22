package com.nhnacademy.townoffice.domain.request;

import lombok.Data;

@Data
public class BirthModify {
    private String birthReporterCode;
    private String email;
    private String phoneNumber;
}
