package com.nhnacademy.townoffice.repository.houseMove;

import com.nhnacademy.townoffice.entity.HouseholdMovementAddress;
import com.nhnacademy.townoffice.entity.QHouseholdMovementAddress;
import com.nhnacademy.townoffice.repository.houseMove.HouseholdMovementRepositoryCustom;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class HouseholdMovementRepositoryImpl extends QuerydslRepositorySupport implements HouseholdMovementRepositoryCustom {
    public HouseholdMovementRepositoryImpl(){
        super(HouseholdMovementAddress.class);
    }

    @Override
    public HouseholdMovementAddress findByHouseholdNumberAndLastAddress(Integer householdNumber) {
        QHouseholdMovementAddress householdMovementAddress = QHouseholdMovementAddress.householdMovementAddress;

        return from(householdMovementAddress)
                .where(householdMovementAddress.pk.householdSerialNumber.eq(householdNumber))
                .orderBy(householdMovementAddress.pk.houseMovementDate.desc())
                .limit(1)
                .fetchOne();
    }
}
