package com.nhnacademy.townoffice.entity;

import com.nhnacademy.townoffice.domain.enumType.CertificationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "certificate_issue")
public class CertificateIssue {
    @Id
    @Column(name = "certificate_confirmation_number")
    private String certificateConfirmationNumber;

    @JoinColumn(name = "resident_serial_number")
    @ManyToOne
    private Resident resident;

    @Column(name = "certificate_type_code")
    @Enumerated(EnumType.STRING)
    private CertificationType certificateTypeCode;

    @Column(name = "certificate_issue_date")
    private LocalDate certificateIssueDate;

}
