package com.nhnacademy.townoffice.service.birth_death;

import com.nhnacademy.townoffice.domain.enumType.BirthDeathCode;
import com.nhnacademy.townoffice.domain.request.BirthDeathRegister;
import com.nhnacademy.townoffice.domain.request.BirthModify;
import com.nhnacademy.townoffice.domain.request.DeathModify;
import com.nhnacademy.townoffice.entity.BirthDeathReportResident;

public interface BirthDeathService {
    BirthDeathReportResident saveBirthDeath(Integer resident, BirthDeathRegister birthDeathRegister);
    BirthDeathReportResident modifyBirth(Integer target, BirthModify birthModify);
    BirthDeathReportResident modifyDeath(Integer target, DeathModify deathModify);
    void deleteBirth(Integer target, BirthDeathCode type);
    void deleteDeath(Integer target, BirthDeathCode type);
    BirthDeathReportResident getBirth(Integer target);
    BirthDeathReportResident getDeath(Integer target);
}
