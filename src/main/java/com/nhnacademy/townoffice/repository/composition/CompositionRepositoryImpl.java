package com.nhnacademy.townoffice.repository.composition;

import com.nhnacademy.townoffice.domain.dto.*;
import com.nhnacademy.townoffice.entity.HouseholdCompositionResident;
import com.nhnacademy.townoffice.entity.QHousehold;
import com.nhnacademy.townoffice.entity.QHouseholdCompositionResident;
import com.nhnacademy.townoffice.entity.QResident;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class CompositionRepositoryImpl extends QuerydslRepositorySupport implements CompositionRepositoryCustom {
    public CompositionRepositoryImpl(){
        super(HouseholdCompositionResident.class);
    }

    @Override
    public AddressDto getCurrentAddress(Integer serialNumber) {
        QHouseholdCompositionResident compositionResident = QHouseholdCompositionResident.householdCompositionResident;
        QHousehold household = QHousehold.household;
        return from(compositionResident)
                .innerJoin(household).fetchJoin()
                .on(household.householdSerialNumber.eq(compositionResident.pk.householdSerialNumber))
                .where(compositionResident.pk.residentSerialNumber.eq(serialNumber))
                .select(new QAddressDto(household.currentHouseAddress))
                .fetchOne();
    }

    @Override
    public HouseholdDto getHouseholdDto(Integer serialNumber) {
        QHouseholdCompositionResident householdCompositionResident = QHouseholdCompositionResident.householdCompositionResident;
        QHousehold household = QHousehold.household;

        return from(householdCompositionResident)
                .innerJoin(household).fetchJoin()
                .on(householdCompositionResident.pk.householdSerialNumber.eq(household.householdSerialNumber))
                .where(householdCompositionResident.pk.residentSerialNumber.eq(serialNumber))
                .select(new QHouseholdDto(household.householdSerialNumber,household.householdResident.residentSerialNumber,household.householdReasonCode, household.householdCompositionDate))
                .fetchOne();
    }

    @Override
    public List<HouseholdCompositionResidentDto> getAllWithAssociations(Integer householdSerialNumber) {
        QHouseholdCompositionResident householdCompositionResident = QHouseholdCompositionResident.householdCompositionResident;
        QResident resident = QResident.resident;

        return from(householdCompositionResident)
                .innerJoin(resident).fetchJoin()
                .on(householdCompositionResident.pk.residentSerialNumber.eq(resident.residentSerialNumber))
                .where(householdCompositionResident.pk.householdSerialNumber.eq(householdSerialNumber))
                .orderBy(householdCompositionResident.changeReason.asc())
                .select(new QHouseholdCompositionResidentDto(householdCompositionResident.householdRelationshipCode, resident.name, resident.residentRegistrationNumber, householdCompositionResident.reportDate, householdCompositionResident.changeReason))
                .fetch();
    }
}
