package com.nhnacademy.townoffice.service.certificate;

import com.nhnacademy.townoffice.domain.dto.CertificateIssueDto;
import com.nhnacademy.townoffice.domain.enumType.CertificationType;
import com.nhnacademy.townoffice.entity.CertificateIssue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CertificateService {
    CertificateIssue saveCertificate(Integer residentSerialNumber, CertificationType type);
    Page<CertificateIssueDto> getCertificatePages(Integer serialNumber,Pageable pageable);
}
