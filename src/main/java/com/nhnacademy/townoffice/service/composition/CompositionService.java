package com.nhnacademy.townoffice.service.composition;

import com.nhnacademy.townoffice.domain.dto.AddressDto;
import com.nhnacademy.townoffice.domain.dto.HouseholdCompositionResidentDto;
import com.nhnacademy.townoffice.domain.forView.TransForHousehold;

import java.util.List;

public interface CompositionService {
    AddressDto getAddressBySerialNumber(Integer serialNumber);
    TransForHousehold getHouseholdBySerialNumber(Integer serialNumber);
    List<HouseholdCompositionResidentDto> getCompositionList(Integer serialNumber);
}
