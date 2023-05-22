package com.nhnacademy.townoffice.service.movement;

import com.nhnacademy.townoffice.domain.forView.TransForMovement;
import com.nhnacademy.townoffice.domain.request.HouseAddressModify;
import com.nhnacademy.townoffice.domain.request.HouseAddressRegister;
import com.nhnacademy.townoffice.entity.HouseholdMovementAddress;

import java.time.LocalDate;
import java.util.List;

public interface MovementService {
    HouseholdMovementAddress saveMovementAddress(Integer householdNumber, HouseAddressRegister houseAddressRegister);
    void modifyMovementAddress(Integer householdNumber, LocalDate date, HouseAddressModify houseAddressModify);
    void deleteMovementAddress(Integer householdNumber, LocalDate date);
    List<TransForMovement> getMovementList(Integer serialNumber);
}
