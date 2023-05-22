package com.nhnacademy.townoffice.controller;

import com.nhnacademy.townoffice.domain.dto.ResidentListDto;
import com.nhnacademy.townoffice.service.resident.ResidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/resident")
public class ResidentViewController {
    private final ResidentService residentService;

    @GetMapping
    public String getResidentListPage(Model model, @PageableDefault(page = 0, size = 5)Pageable pageable){
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        Page<ResidentListDto> residentDtos = residentService.findResidentList(pageable);
        model.addAttribute("residents", residentDtos);
        model.addAttribute("lastPage", residentService.getResidentList().size());
        return "residentList";
    }
    @PostMapping("/delete/{serialNumber}")
    public String deleteResident(@PathVariable("serialNumber") Integer serialNumber){
        residentService.deleteResident(serialNumber);
        return "redirect:/resident";
    }
}
