package com.nhnacademy.townoffice.controller.rest;

import com.nhnacademy.townoffice.domain.enumType.BirthDeathCode;
import com.nhnacademy.townoffice.domain.request.BirthModify;
import com.nhnacademy.townoffice.domain.request.BirthDeathRegister;
import com.nhnacademy.townoffice.entity.BirthDeathReportResident;
import com.nhnacademy.townoffice.service.birth_death.BirthDeathService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/residents/{serialNumber}/birth")
@RequiredArgsConstructor
public class BirthController {

    private final BirthDeathService birthDeathSerivce;

    @PostMapping
    public HttpEntity<BirthDeathReportResident> saveBirth(@PathVariable("serialNumber") Integer resident,
                                                          @RequestBody BirthDeathRegister birthDeathRegister){
        BirthDeathReportResident birth = birthDeathSerivce.saveBirthDeath(resident, birthDeathRegister);

        return new ResponseEntity<>(birth, HttpStatus.CREATED);
    }


    @PutMapping("/{targetSerialNumber}")
    public HttpEntity<BirthDeathReportResident> modifyBirth(@PathVariable("serialNumber")Integer reporter,
                                                            @PathVariable("targetSerialNumber") Integer target,
                                                            @RequestBody BirthModify birthModify){
        BirthDeathReportResident birth = birthDeathSerivce.modifyBirth(target,birthModify);
        return new ResponseEntity<>(birth, HttpStatus.CREATED);
    }

    @DeleteMapping("/{targetSerialNumber}")
    public HttpEntity<Void> deleteBirth(@PathVariable("serialNumber")Integer reporter,
                                        @PathVariable("targetSerialNumber") Integer target){
        birthDeathSerivce.deleteBirth(target, BirthDeathCode.출생);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
