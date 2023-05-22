package com.nhnacademy.townoffice.controller;

import com.nhnacademy.townoffice.domain.dto.AddressDto;
import com.nhnacademy.townoffice.domain.dto.CertificateIssueDto;
import com.nhnacademy.townoffice.domain.dto.HouseholdCompositionResidentDto;
import com.nhnacademy.townoffice.domain.dto.ParentsDto;
import com.nhnacademy.townoffice.domain.enumType.FamilyType;
import com.nhnacademy.townoffice.domain.forView.TransForHousehold;
import com.nhnacademy.townoffice.domain.forView.TransForMovement;
import com.nhnacademy.townoffice.domain.forView.TransForResident;
import com.nhnacademy.townoffice.domain.enumType.CertificationType;
import com.nhnacademy.townoffice.entity.BirthDeathReportResident;
import com.nhnacademy.townoffice.entity.CertificateIssue;
import com.nhnacademy.townoffice.entity.FamilyRelationship;
import com.nhnacademy.townoffice.entity.Resident;
import com.nhnacademy.townoffice.service.birth_death.BirthDeathService;
import com.nhnacademy.townoffice.service.birth_death.BirthDeathServiceImpl;
import com.nhnacademy.townoffice.service.certificate.CertificateService;
import com.nhnacademy.townoffice.service.composition.CompositionService;
import com.nhnacademy.townoffice.service.family.FamilyRelationshipService;
import com.nhnacademy.townoffice.service.movement.MovementService;
import com.nhnacademy.townoffice.service.resident.ResidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@RequestMapping("/certificate")
public class CertificateController {
    private final CertificateService certificateService;
    private final CompositionService compositionService;
    private final ResidentService residentService;
    private final MovementService movementService;
    private final BirthDeathService birthDeathService;
    private final FamilyRelationshipService familyRelationshipService;

    @PostMapping("/family/{serialNumber}")
    public String getFamilyCertificate(@PathVariable("serialNumber") Integer serialNumber,
                                       Model model){
        CertificateIssue certificateIssue = certificateService.saveCertificate(serialNumber, CertificationType.가족관계증명서);
        model.addAttribute("certificate", certificateIssue);

        AddressDto addressDto = compositionService.getAddressBySerialNumber(serialNumber);
        model.addAttribute("addressDto", addressDto);

        List<TransForResident> residentDtos = residentService.getResidentDtos(serialNumber);
        model.addAttribute("residentDtos", residentDtos);
        return "familyCertificate";
    }

    @PostMapping("/person/{serialNumber}")
    public String getPersonCertificate(@PathVariable("serialNumber") Integer serialNumber, Model model){
        CertificateIssue certificateIssue = certificateService.saveCertificate(serialNumber, CertificationType.주민등록등본);
        model.addAttribute("certificate", certificateIssue);
        TransForHousehold translateHousehold = compositionService.getHouseholdBySerialNumber(serialNumber);
        model.addAttribute("translateHousehold", translateHousehold);
        List<TransForMovement> transForMovementList = movementService.getMovementList(serialNumber);
        model.addAttribute("MovementList", transForMovementList);
        List<HouseholdCompositionResidentDto> compositionList = compositionService.getCompositionList(serialNumber);
        model.addAttribute("compositionList", compositionList);
        return "personCertificate";

    }

    @PostMapping("/birth/{serialNumber}")
    public String getBirthCertificate(@PathVariable("serialNumber") Integer serialNumber, Model model){
        CertificateIssue certificateIssue = certificateService.saveCertificate(serialNumber, CertificationType.출생신고서);
        BirthDeathReportResident birthDeathReportResident = birthDeathService.getBirth(serialNumber);
        model.addAttribute("birthResident", birthDeathReportResident);

        Resident resident = residentService.getResident(birthDeathReportResident.getTargetResident().getResidentSerialNumber());
        model.addAttribute("target", resident);
        String birthDate = resident.getBirthDate().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 hh시 mm분"));
        model.addAttribute("birthDate", birthDate);

        List<FamilyRelationship> familyRelationshipList = familyRelationshipService.getAllFamilyRelationshipByBase(resident.getResidentSerialNumber());
        ParentsDto father = new ParentsDto(0, "-", FamilyType.부, "-");
        ParentsDto mother = new ParentsDto(0, "-", FamilyType.모, "-");
        for(FamilyRelationship f:familyRelationshipList){
            if(f.getFamilyRelationshipCode().equals(FamilyType.부)){
                father=familyRelationshipService.getParent(resident.getResidentSerialNumber(), FamilyType.부);
            }
            else if(f.getFamilyRelationshipCode().equals(FamilyType.모)){
                mother = familyRelationshipService.getParent(resident.getResidentSerialNumber(), FamilyType.모);
            }
        }
        model.addAttribute("father",father);
        model.addAttribute("mother", mother);
        return "birthCertificate";
    }

    @PostMapping("/death/{serialNumber}")
    public String getDeathCertificate(@PathVariable("serialNumber") Integer serialNumber, Model model){
        CertificateIssue certificateIssue = certificateService.saveCertificate(serialNumber, CertificationType.사망신고서);
        BirthDeathReportResident birthDeathReportResident = birthDeathService.getDeath(serialNumber);
        model.addAttribute("deathResident", birthDeathReportResident);
        Resident resident = residentService.getResident(birthDeathReportResident.getPk().getResidentSerialNumber());
        model.addAttribute("target", resident);
        String deathDate = resident.getDeathDate().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 hh시 mm분"));
        model.addAttribute("deathDate", deathDate);
        return "deathCertificate";
    }
    @GetMapping("/list/{serialNumber}")
    public String getCertificatesPage(@PathVariable("serialNumber") Integer serialNumber,
                                      @PageableDefault(page = 0, size = 5)Pageable pageable,
                                      Model model){
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        Page<CertificateIssueDto> certificates = certificateService.getCertificatePages(serialNumber,pageable);
        model.addAttribute("serialNumber", serialNumber);
        model.addAttribute("certificates",certificates);
        return "certificateList";
    }
}
