package com.nhnacademy.townoffice.repository.houseMove;

import com.nhnacademy.townoffice.entity.HouseholdMovementAddress;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.time.LocalDate;

@NoRepositoryBean
public interface HouseholdMovementRepositoryCustom {
    public HouseholdMovementAddress findByHouseholdNumberAndLastAddress(Integer householdNumber);

}
