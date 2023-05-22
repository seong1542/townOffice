package com.nhnacademy.townoffice.repository.composition;

import com.nhnacademy.townoffice.domain.dto.AddressDto;
import com.nhnacademy.townoffice.domain.dto.HouseholdCompositionResidentDto;
import com.nhnacademy.townoffice.domain.dto.HouseholdDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface CompositionRepositoryCustom {
    AddressDto getCurrentAddress(Integer serialNumber);
    HouseholdDto getHouseholdDto(Integer serialNumber);

    List<HouseholdCompositionResidentDto> getAllWithAssociations(Integer householdSerialNumber);
}
