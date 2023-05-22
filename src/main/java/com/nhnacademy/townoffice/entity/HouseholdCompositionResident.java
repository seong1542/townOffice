package com.nhnacademy.townoffice.entity;

import com.nhnacademy.townoffice.domain.enumType.FamilyType;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "household_composition_resident")
public class HouseholdCompositionResident {
    @EmbeddedId
    private Pk pk;

    @JoinColumn(name = "resident_serial_number")
    @MapsId("residentSerialNumber")
    @ManyToOne
    private Resident resident;

    @ManyToOne
    @MapsId("householdSerialNumber")
    @JoinColumn(name = "household_serial_number")
    private Household household;
    @Column(name = "report_date")
    private LocalDate reportDate;
    @Column(name = "household_relationship_code")
    @Enumerated(EnumType.STRING)
    private FamilyType householdRelationshipCode;

    @Column(name = "household_composition_change_reason_code")
    private String changeReason;

    @Embeddable
    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class Pk implements Serializable{
        private Integer householdSerialNumber;
        private Integer residentSerialNumber;
    }
}
