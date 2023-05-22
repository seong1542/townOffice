package com.nhnacademy.townoffice.repository.birth;

import com.nhnacademy.townoffice.entity.BirthDeathReportResident;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BirthDeathRepositoryCustom {
    BirthDeathReportResident getByBirthTypeCode(Integer targetSerialNumber);
    BirthDeathReportResident getByDeathTypeCode(Integer targetSerialNumber);
}
