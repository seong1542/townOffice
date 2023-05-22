package com.nhnacademy.townoffice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nhnacademy.townoffice.domain.enumType.BirthPlace;
import com.nhnacademy.townoffice.domain.enumType.Gender;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "resident")
@AllArgsConstructor
@Builder
public class Resident {
    @Id
    @Column(name = "resident_serial_number")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer residentSerialNumber;

    @Setter
    @Column(nullable = false)
    private String name;

    @Setter
    @Column(name = "resident_registration_number",nullable = false)
    private String residentRegistrationNumber;
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "gender_code",nullable = false)
    private Gender genderCode;

    @Setter
    @Column(name = "birth_date",nullable = false)
    private LocalDateTime birthDate;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "birth_place_code",nullable = false)
    private BirthPlace birthPlaceCode;
    @Setter
    @Column(name = "registration_base_address",nullable = false)
    private String registrationBaseAddress;

    @Setter
    @Column(name = "death_date")
    private LocalDateTime deathDate;
    @Setter
    @Column(name = "death_place_code")
    private String deathPlaceCode;
    @Setter
    @Column(name = "death_place_address")
    private String deathPlaceAddress;

    @JsonIgnore
    @OneToMany(mappedBy = "baseResident", cascade = CascadeType.REMOVE)
    private List<FamilyRelationship> familyRelationshipList;
    @JsonIgnore
    @OneToMany(mappedBy = "familyResident", cascade = CascadeType.REMOVE)
    private List<FamilyRelationship> familyRelationships;
    @JsonIgnore
    @OneToMany(mappedBy = "targetResident", cascade = CascadeType.REMOVE)
    private List<BirthDeathReportResident> birthDeathReportResidentList;
    @JsonIgnore
    @OneToMany(mappedBy = "reportResident", cascade = CascadeType.REMOVE)
    private List<BirthDeathReportResident> birthDeathReportResidents;
    @OneToMany(mappedBy = "householdResident", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Household> householdList;

    @JsonIgnore
    @OneToMany(mappedBy = "resident", cascade = CascadeType.REMOVE)
    private List<HouseholdCompositionResident> householdCompositionResidentList;
    @JsonIgnore
    @OneToMany(mappedBy = "resident", cascade = CascadeType.REMOVE)
    private List<CertificateIssue> certificateIssueList;
}