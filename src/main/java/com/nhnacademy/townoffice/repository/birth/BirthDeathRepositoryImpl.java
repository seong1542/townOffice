package com.nhnacademy.townoffice.repository.birth;

import com.nhnacademy.townoffice.domain.enumType.BirthDeathCode;
import com.nhnacademy.townoffice.entity.BirthDeathReportResident;
import com.nhnacademy.townoffice.entity.QBirthDeathReportResident;
import com.nhnacademy.townoffice.repository.birth.BirthDeathRepositoryCustom;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class BirthDeathRepositoryImpl extends QuerydslRepositorySupport implements BirthDeathRepositoryCustom {
    public BirthDeathRepositoryImpl(){
        super(BirthDeathReportResident.class);
    }

    @Override
    public BirthDeathReportResident getByBirthTypeCode(Integer targetSerialNumber) {
        QBirthDeathReportResident birth = QBirthDeathReportResident.birthDeathReportResident;
        return from(birth)
                .where(birth.pk.residentSerialNumber.eq(targetSerialNumber))
                .where(birth.pk.birthDeathTypeCode.eq(BirthDeathCode.출생))
                .fetchOne();
    }

    @Override
    public BirthDeathReportResident getByDeathTypeCode(Integer targetSerialNumber) {
        QBirthDeathReportResident birth = QBirthDeathReportResident.birthDeathReportResident;
        return from(birth)
                .where(birth.pk.residentSerialNumber.eq(targetSerialNumber))
                .where(birth.pk.birthDeathTypeCode.eq(BirthDeathCode.사망))
                .fetchOne();
    }
}
