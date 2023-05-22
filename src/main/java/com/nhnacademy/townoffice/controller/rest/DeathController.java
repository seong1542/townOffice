package com.nhnacademy.townoffice.controller.rest;

import com.nhnacademy.townoffice.domain.enumType.BirthDeathCode;
import com.nhnacademy.townoffice.domain.request.BirthDeathRegister;
import com.nhnacademy.townoffice.domain.request.DeathModify;
import com.nhnacademy.townoffice.entity.BirthDeathReportResident;
import com.nhnacademy.townoffice.service.birth_death.BirthDeathService;
import com.nhnacademy.townoffice.service.birth_death.BirthDeathServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/residents/{serialNumber}/death")
@RequiredArgsConstructor
public class DeathController {
    private final BirthDeathService birthDeathService;

    @PostMapping
    public HttpEntity<BirthDeathReportResident> saveBirth(@PathVariable("serialNumber") Integer resident,
                                                          @RequestBody BirthDeathRegister birthDeathRegister){
        BirthDeathReportResident birth = birthDeathService.saveBirthDeath(resident, birthDeathRegister);

        return new ResponseEntity<>(birth, HttpStatus.CREATED);
    }


    @Transactional
    @PutMapping("/{targetSerialNumber}")
    public HttpEntity<BirthDeathReportResident> modifyBirth(@PathVariable("serialNumber")Integer reporter,
                                                            @PathVariable("targetSerialNumber") Integer target,
                                                            @RequestBody DeathModify deathModify){
        BirthDeathReportResident birth = birthDeathService.modifyDeath(target,deathModify);
        return new ResponseEntity<>(birth, HttpStatus.CREATED);
    }

    @Transactional
    @DeleteMapping("/{targetSerialNumber}")
    public HttpEntity<Void> deleteBirth(@PathVariable("serialNumber")Integer reporter,
                                        @PathVariable("targetSerialNumber") Integer target){
        birthDeathService.deleteDeath(target, BirthDeathCode.사망);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
