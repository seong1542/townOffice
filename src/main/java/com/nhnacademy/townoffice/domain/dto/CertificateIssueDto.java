package com.nhnacademy.townoffice.domain.dto;

import com.nhnacademy.townoffice.domain.enumType.CertificationType;
import com.nhnacademy.townoffice.entity.CertificateIssue;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;

public interface CertificateIssueDto {
    String getCertificateConfirmationNumber();
    @Value("#{target.resident.name}")
    String getResident();
    CertificationType getCertificateTypeCode();
    LocalDate getCertificateIssueDate();
    static CertificateIssueDto toCertofocateIssueDto(CertificateIssue certificateIssue){
        return new CertificateIssueDto() {
            @Override
            public String getCertificateConfirmationNumber() {
                return certificateIssue.getCertificateConfirmationNumber();
            }

            @Override
            public String getResident() {
                return certificateIssue.getResident().getName();
            }

            @Override
            public CertificationType getCertificateTypeCode() {
                return certificateIssue.getCertificateTypeCode();
            }

            @Override
            public LocalDate getCertificateIssueDate() {
                return certificateIssue.getCertificateIssueDate();
            }
        };
    }
}
