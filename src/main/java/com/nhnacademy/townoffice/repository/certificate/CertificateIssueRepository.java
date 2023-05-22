package com.nhnacademy.townoffice.repository.certificate;

import com.nhnacademy.townoffice.entity.CertificateIssue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CertificateIssueRepository extends JpaRepository<CertificateIssue, String> {
    @Query(value = "select c from CertificateIssue c where c.resident.residentSerialNumber = :residentSerialNumber order by c.certificateIssueDate DESC ")
    Page<CertificateIssue> findByResident_ResidentSerialNumberOrderByCertificateIssueDateDesc(@Param("residentSerialNumber") Integer residentSerialNumber, Pageable pageable);
}
