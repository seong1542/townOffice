package com.nhnacademy.townoffice.controller.rest;

import com.nhnacademy.townoffice.domain.request.HouseAddressModify;
import com.nhnacademy.townoffice.domain.request.HouseAddressRegister;
import com.nhnacademy.townoffice.entity.HouseholdMovementAddress;
import com.nhnacademy.townoffice.service.movement.MovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/household/{householdSerialNumber}/movement")
@RequiredArgsConstructor
public class HouseMovementController {
    private final MovementService movementService;

    @PostMapping
    public HttpEntity<HouseholdMovementAddress> saveMovementAddress(@PathVariable("householdSerialNumber") Integer householdNum,
                                                                    @RequestBody HouseAddressRegister houseAddressRegister){
        HouseholdMovementAddress movementAddress = movementService.saveMovementAddress(householdNum,houseAddressRegister);
        return new ResponseEntity<>(movementAddress, HttpStatus.CREATED);
    }

    @PutMapping("/{reportDate}")
    public HttpEntity<Void> modifyMovementAddress(@PathVariable("householdSerialNumber") Integer householdNum,
                                                                      @PathVariable("reportDate") String reportDate,
                                                                      @RequestBody HouseAddressModify houseAddressModify){
        LocalDate date = LocalDate.parse(reportDate, DateTimeFormatter.ofPattern("yyyyMMdd"));
        movementService.modifyMovementAddress(householdNum, date, houseAddressModify);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{reportDate}")
    public HttpEntity<Void> deleteMovementAddress(@PathVariable("householdSerialNumber") Integer householdNum,
                                                  @PathVariable("reportDate") String reportDate){
        LocalDate date = LocalDate.parse(reportDate, DateTimeFormatter.ofPattern("yyyyMMdd"));
        movementService.deleteMovementAddress(householdNum,date);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
