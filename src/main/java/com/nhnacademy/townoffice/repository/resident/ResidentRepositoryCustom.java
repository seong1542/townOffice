package com.nhnacademy.townoffice.repository.resident;

import com.nhnacademy.townoffice.domain.dto.ResidentFamilyDto;
import com.nhnacademy.townoffice.domain.dto.ResidentListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface ResidentRepositoryCustom {
    Page<ResidentListDto> findResidents(Pageable pageable);
    List<ResidentFamilyDto> findResidentDtoByResidentRegistrationNumber(Integer residentSerialNum);
}
