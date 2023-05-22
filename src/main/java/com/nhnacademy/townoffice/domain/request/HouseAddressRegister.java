package com.nhnacademy.townoffice.domain.request;

import com.nhnacademy.townoffice.domain.enumType.AnswerType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class HouseAddressRegister {
    private LocalDate houseMovementDate;
    private String houseMovementAddress;
}
