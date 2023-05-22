package com.nhnacademy.townoffice.domain.request;

import com.nhnacademy.townoffice.domain.enumType.BirthPlace;
import com.nhnacademy.townoffice.domain.enumType.Gender;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
public class ResidentRegister {
    private String name;
    private String residentRegistrationNumber;
    private Gender genderCode;
    private LocalDateTime birthDate;
    private BirthPlace birthPlaceCode;
    private String registrationBaseAddress;
}
