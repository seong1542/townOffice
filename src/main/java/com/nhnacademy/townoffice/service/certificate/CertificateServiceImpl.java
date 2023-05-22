package com.nhnacademy.townoffice.service.certificate;

import com.nhnacademy.townoffice.domain.dto.CertificateIssueDto;
import com.nhnacademy.townoffice.domain.enumType.CertificationType;
import com.nhnacademy.townoffice.entity.CertificateIssue;
import com.nhnacademy.townoffice.entity.Resident;
import com.nhnacademy.townoffice.exception.SomethingNotFoundException;
import com.nhnacademy.townoffice.repository.certificate.CertificateIssueRepository;
import com.nhnacademy.townoffice.repository.resident.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CertificateServiceImpl implements CertificateService {
    private final CertificateIssueRepository certificateRepository;
    private final ResidentRepository residentRepository;

    public CertificateIssue saveCertificate(Integer residentSerialNumber, CertificationType type){
        long confirmNum = (long) (Math.random()*100000000);
        long confirmNum2 = (long) (Math.random() * 100000000);
        String randNumber = String.format("%08d",confirmNum) +"-" +String.format("%08d",confirmNum2);
        Resident resident = residentRepository.findResidentByResidentSerialNumber(residentSerialNumber);
        if(Objects.isNull(resident)){
            throw new SomethingNotFoundException("주민이 존재하지 않습니다");
        }
        CertificateIssue certificateIssue = new CertificateIssue(randNumber, resident, type, LocalDate.now());
        return certificateRepository.save(certificateIssue);
    }

    public Page<CertificateIssueDto> getCertificatePages(Integer serialNumber,Pageable pageable){
        List<CertificateIssueDto> certificateIssueDtos= certificateRepository.findByResident_ResidentSerialNumberOrderByCertificateIssueDateDesc(serialNumber,pageable).stream().map(CertificateIssueDto::toCertofocateIssueDto).collect(Collectors.toList());
        Page<CertificateIssue> certificateIssueList = certificateRepository.findByResident_ResidentSerialNumberOrderByCertificateIssueDateDesc(serialNumber, pageable);
        long totalCount = certificateIssueList.getTotalElements();
        return new PageImpl<>(certificateIssueDtos, pageable, totalCount);
    }
}
