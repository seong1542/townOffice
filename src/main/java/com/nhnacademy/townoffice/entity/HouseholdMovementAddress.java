package com.nhnacademy.townoffice.entity;

import com.nhnacademy.townoffice.domain.enumType.AnswerType;
import lombok.*;
import org.springframework.objenesis.instantiator.android.AndroidSerializationInstantiator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "household_movement_address")
public class HouseholdMovementAddress {
    @EmbeddedId
    private Pk pk;

    @JoinColumn(name = "household_serial_number")
    @MapsId("householdSerialNumber")
    @ManyToOne
    private Household household;

    @Column(name = "house_movement_address")
    private String houseMovementAddress;

    @Column(name = "last_address_yn")
    @Enumerated(EnumType.STRING)
    private AnswerType lastAddress;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    public static class Pk implements Serializable{

        private Integer householdSerialNumber;
        @Column(name = "house_movement_report_date")
        private LocalDate houseMovementDate;
    }
}
