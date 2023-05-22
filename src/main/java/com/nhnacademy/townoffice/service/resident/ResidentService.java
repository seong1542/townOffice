package com.nhnacademy.townoffice.service.resident;

import com.nhnacademy.townoffice.domain.dto.ResidentListDto;
import com.nhnacademy.townoffice.domain.forView.TransForResident;
import com.nhnacademy.townoffice.domain.request.ResidentRegister;
import com.nhnacademy.townoffice.entity.Resident;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ResidentService {
    List<Resident> getResidentList();
    Resident saveResident(ResidentRegister residentRegister);
    Resident modifyResident(Resident resident);
    Resident getResident(Integer serialNumber);
    Page<ResidentListDto> findResidentList(Pageable pageable);
    List<TransForResident> getResidentDtos(Integer residentSerialNum);
    void deleteResident(Integer serialNumber);
}
