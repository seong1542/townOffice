package com.nhnacademy.townoffice.service.houshold;

import com.nhnacademy.townoffice.domain.request.HouseholdRegister;
import com.nhnacademy.townoffice.entity.Household;

import java.util.List;

public interface HouseholdService {
    Household saveHousehold(HouseholdRegister householdRegister);
    List<Household> getHouseholds();
    void deleteHousehold(Integer houseNum);
}
