package com.nhnacademy.townoffice.entity;

import com.nhnacademy.townoffice.domain.enumType.FamilyType;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "family_relationship")
public class FamilyRelationship {
    @EmbeddedId
    private Pk pk;

    @MapsId("baseResidentSerialNumber")
    @ManyToOne
    @JoinColumn(name = "base_resident_serial_number")
    private Resident baseResident;

    @MapsId("familyResidentSerialNumber")
    @ManyToOne
    @JoinColumn(name = "family_resident_serial_number")
    private Resident familyResident;

    @Column(name = "family_relationship_code",nullable = false)
    @Enumerated(EnumType.STRING)
    private FamilyType familyRelationshipCode;

    @Getter
    @Setter
    @NoArgsConstructor
    @Embeddable
    @EqualsAndHashCode
    @AllArgsConstructor
    public static class Pk implements Serializable{
        @Column(name = "family_resident_serial_number",nullable = false)
        private Integer familyResidentSerialNumber;

        @Column(name = "base_resident_serial_number",nullable = false)
        private Integer baseResidentSerialNumber;
    }

}
