package com.nhnacademy.townoffice.entity;

import com.nhnacademy.townoffice.domain.enumType.BirthDeathCode;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "birth_death_report_resident")
public class BirthDeathReportResident {

    @EmbeddedId
    private Pk pk;
    @MapsId("residentSerialNumber")
    @ManyToOne
    @JoinColumn(name = "resident_serial_number")
    private Resident targetResident;

    @JoinColumn(name = "report_resident_serial_number",nullable = false)
    @ManyToOne
    private Resident reportResident;

    @Column(name = "birth_death_report_date",nullable = false)
    private LocalDate reportDate;

    @Column(name = "birth_report_qualifications_code")
    private String birthReporterCode;
    @Column(name = "death_report_qualifications_code")
    private String deathReporterCode;
    @Column(name = "email_address")
    private String email;
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Getter
    @Setter
    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    public static class Pk implements Serializable{
        private Integer residentSerialNumber;
        @Column(name = "birth_death_type_code")
        @Enumerated(EnumType.STRING)
        private BirthDeathCode birthDeathTypeCode;

    }
}
