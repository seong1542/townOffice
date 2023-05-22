package com.nhnacademy.townoffice.controller.rest;

import com.nhnacademy.townoffice.domain.request.FamilyModify;
import com.nhnacademy.townoffice.domain.request.FamilyRegister;
import com.nhnacademy.townoffice.entity.FamilyRelationship;
import com.nhnacademy.townoffice.service.family.FamilyRelationshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/residents/{serialNumber}/relationship")
@RequiredArgsConstructor
public class FamilyController {
    private final FamilyRelationshipService familyRelationshipService;

    @GetMapping("/{familySerialNumber}")
    public HttpEntity<FamilyRelationship> getFamily(@PathVariable("serialNumber") Integer base,
                                                                @PathVariable("familySerialNumber") Integer familyNumber){
        FamilyRelationship familyRelationship = familyRelationshipService.getFamilyRelationship(base,familyNumber);
        return new ResponseEntity<>(familyRelationship, HttpStatus.OK);
    }

    @PostMapping
    public HttpEntity<FamilyRelationship> saveFamily(@PathVariable("serialNumber") Integer base,
                                                   @RequestBody FamilyRegister familyRegister)
    {
        FamilyRelationship familyRelationship = familyRelationshipService.saveFamilyRelationship(base, familyRegister);
        return new ResponseEntity<>(familyRelationship, HttpStatus.CREATED);
    }

    @PutMapping("/{familySerialNumber}")
    public HttpEntity<FamilyRelationship> modifyFamily(@PathVariable("serialNumber") Integer base,
                                                       @PathVariable("familySerialNumber") Integer familyNum,
                                                       @RequestBody FamilyModify familyModify){
        FamilyRelationship familyRelationship = familyRelationshipService.getFamilyRelationship(base, familyNum);
        FamilyRelationship modified = familyRelationshipService.modifyFamilyRelationship(familyRelationship,familyModify);
        return new ResponseEntity<>(modified, HttpStatus.CREATED);
    }

    @DeleteMapping("/{familySerialNumber}")
    public HttpEntity<Void> deleteFamily(@PathVariable("serialNumber") Integer base,
                                         @PathVariable("familySerialNumber") Integer familyNum){
        familyRelationshipService.removeFamily(base, familyNum);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
