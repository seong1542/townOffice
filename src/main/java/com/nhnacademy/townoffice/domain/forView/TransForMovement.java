package com.nhnacademy.townoffice.domain.forView;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TransForMovement {
    private LocalDate reportDate;
    private String houseMovementAddress;
    private String addressStatus;
}
