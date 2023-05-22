package com.nhnacademy.townoffice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "household")
public class Household {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "household_serial_number")
    private Integer householdSerialNumber;

    @ManyToOne
    @Setter
    @JoinColumn(name = "household_resident_serial_number")
    private Resident householdResident;

    @Setter
    @Column(name = "household_composition_date")
    private LocalDate householdCompositionDate;

    @Setter
    @Column(name = "household_composition_reason_code")
    private String householdReasonCode;

    @Setter
    @Column(name = "current_house_movement_address")
    private String currentHouseAddress;

    @JsonIgnore
    @OneToMany(mappedBy = "household", cascade = CascadeType.REMOVE)
    private List<HouseholdMovementAddress> householdMovementAddressList;

    @JsonIgnore
    @OneToMany(mappedBy = "household", cascade = CascadeType.REMOVE)
    private List<HouseholdCompositionResident> householdCompositionResidentList;
}
