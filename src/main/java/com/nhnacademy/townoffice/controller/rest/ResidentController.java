package com.nhnacademy.townoffice.controller.rest;

import com.nhnacademy.townoffice.domain.request.ResidentRegister;
import com.nhnacademy.townoffice.entity.Resident;
import com.nhnacademy.townoffice.service.resident.ResidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/residents")
@RequiredArgsConstructor
public class ResidentController {
    private final ResidentService residentService;

    @GetMapping
    public HttpEntity<List<Resident>> getResidents(){
        return new ResponseEntity<>(residentService.getResidentList(), HttpStatus.OK);
    }

    @PostMapping
    public HttpEntity<Void> saveResident(@RequestBody ResidentRegister residentRegister){
        residentService.saveResident(residentRegister);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{serialNumber}")
    public HttpEntity<Void> updateResident(@PathVariable("serialNumber") Integer serialNumber,
                                           @RequestBody ResidentRegister residentRegister){
        Resident resident = residentService.getResident(serialNumber);
        resident.setName(residentRegister.getName());
        resident.setResidentRegistrationNumber(residentRegister.getResidentRegistrationNumber());
        resident.setGenderCode(residentRegister.getGenderCode());
        resident.setBirthDate(residentRegister.getBirthDate());
        resident.setBirthPlaceCode(residentRegister.getBirthPlaceCode());
        resident.setRegistrationBaseAddress(residentRegister.getRegistrationBaseAddress());

        residentService.modifyResident(resident);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
