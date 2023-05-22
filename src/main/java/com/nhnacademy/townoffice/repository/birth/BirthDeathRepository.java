package com.nhnacademy.townoffice.repository.birth;

import com.nhnacademy.townoffice.domain.enumType.BirthDeathCode;
import com.nhnacademy.townoffice.entity.BirthDeathReportResident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BirthDeathRepository extends BirthDeathRepositoryCustom,JpaRepository<BirthDeathReportResident, BirthDeathReportResident.Pk> {
}
