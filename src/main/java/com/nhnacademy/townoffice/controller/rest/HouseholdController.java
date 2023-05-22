package com.nhnacademy.townoffice.controller.rest;

import com.nhnacademy.townoffice.domain.request.HouseholdRegister;
import com.nhnacademy.townoffice.entity.Household;
import com.nhnacademy.townoffice.service.houshold.HouseholdService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/household")
@RequiredArgsConstructor
public class HouseholdController {
    private final HouseholdService householdService;

    @GetMapping
    public HttpEntity<List<Household>> getHouseholds(){
        List<Household> householdList = householdService.getHouseholds();
        return new ResponseEntity<>(householdList, HttpStatus.OK);
    }

    @PostMapping
    public HttpEntity<Household> saveHousehold(@RequestBody HouseholdRegister householdRegister){
        Household household = householdService.saveHousehold(householdRegister);
        return new ResponseEntity<>(household, HttpStatus.CREATED);
    }

    @DeleteMapping("/{householdSerialNumber}")
    public HttpEntity<Void> deleteHousehold(@PathVariable("householdSerialNumber") Integer houseNum){
        householdService.deleteHousehold(houseNum);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
