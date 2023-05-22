package com.nhnacademy.townoffice.service.birth_death;

import com.nhnacademy.townoffice.domain.enumType.BirthDeathCode;
import com.nhnacademy.townoffice.domain.request.BirthModify;
import com.nhnacademy.townoffice.domain.request.BirthDeathRegister;
import com.nhnacademy.townoffice.domain.request.DeathModify;
import com.nhnacademy.townoffice.entity.BirthDeathReportResident;
import com.nhnacademy.townoffice.entity.Resident;
import com.nhnacademy.townoffice.exception.SomethingNotFoundException;
import com.nhnacademy.townoffice.repository.birth.BirthDeathRepository;
import com.nhnacademy.townoffice.repository.resident.ResidentRepository;
import com.nhnacademy.townoffice.service.birth_death.BirthDeathService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BirthDeathServiceImpl implements BirthDeathService {
    private final BirthDeathRepository birthDeathRepository;
    private final ResidentRepository residentRepository;

    //save
    public BirthDeathReportResident saveBirthDeath(Integer resident, BirthDeathRegister birthDeathRegister){
        Resident targetResident = residentRepository.findResidentByResidentSerialNumber(birthDeathRegister.getResidentSerialNumber());
        Resident reporterResident = residentRepository.findResidentByResidentSerialNumber(resident);
        if(!residentRepository.existsById(resident) || !residentRepository.existsById(birthDeathRegister.getResidentSerialNumber())){
            throw new SomethingNotFoundException("주민이 존재하지 않습니다.");
        }
        BirthDeathReportResident birthDeathReportResident=
                new BirthDeathReportResident(new BirthDeathReportResident.Pk(birthDeathRegister.getResidentSerialNumber(),
                        birthDeathRegister.getBirthDeathTypeCode()),
                        targetResident,
                        reporterResident,
                        birthDeathRegister.getReportDate(),
                        birthDeathRegister.getBirthReporterCode(),
                        birthDeathRegister.getDeathReporterCode(),
                        birthDeathRegister.getEmail(),
                        birthDeathRegister.getPhoneNumber());
        if(birthDeathReportResident.getPk().getBirthDeathTypeCode().equals(BirthDeathCode.사망)){
            targetResident.setDeathPlaceCode(birthDeathRegister.getDeathPlace());
            targetResident.setDeathPlaceAddress(birthDeathRegister.getDeathAddress());
            targetResident.setDeathDate(birthDeathRegister.getReportDate().atTime(00,00,00));
            residentRepository.save(targetResident);
        }
        return birthDeathRepository.save(birthDeathReportResident);
    }
    //modify
    public BirthDeathReportResident modifyBirth(Integer target, BirthModify birthModify){
        BirthDeathReportResident birthDeathReportResident = birthDeathRepository.getByBirthTypeCode(target);
        if(Objects.isNull(birthDeathReportResident)){
            throw new SomethingNotFoundException("출생신고가 되지 않은 존재입니다.");
        }
        birthDeathReportResident.setBirthReporterCode(birthModify.getBirthReporterCode());
        birthDeathReportResident.setEmail(birthModify.getEmail());
        birthDeathReportResident.setPhoneNumber(birthModify.getPhoneNumber());

        return birthDeathRepository.save(birthDeathReportResident);
    }
    public BirthDeathReportResident modifyDeath(Integer target, DeathModify deathModify){
        BirthDeathReportResident birthDeathReportResident = birthDeathRepository.getByDeathTypeCode(target);
        if(Objects.isNull(birthDeathReportResident)){
            throw new SomethingNotFoundException("사망신고가 되지 않은 존재입니다.");
        }
        birthDeathReportResident.setDeathReporterCode(deathModify.getDeathReporterCode());
        birthDeathReportResident.setEmail(deathModify.getEmail());
        birthDeathReportResident.setPhoneNumber(deathModify.getPhoneNumber());

        return birthDeathRepository.save(birthDeathReportResident);
    }
    //delete
    public void deleteBirth(Integer target, BirthDeathCode type){
        BirthDeathReportResident birth = birthDeathRepository.getByBirthTypeCode(target);
        if(Objects.isNull(birth)){
            throw new SomethingNotFoundException("출생신고가 되지 않은 존재입니다.");
        }
        birthDeathRepository.deleteById(new BirthDeathReportResident.Pk(target, type));
    }
    public void deleteDeath(Integer target, BirthDeathCode type){
        BirthDeathReportResident death = birthDeathRepository.getByDeathTypeCode(target);
        if(Objects.isNull(death)){
            throw new SomethingNotFoundException("사망신고가 되지 않은 존재입니다.");
        }
        Resident resident = residentRepository.findResidentByResidentSerialNumber(target);
        resident.setDeathDate(null);
        resident.setDeathPlaceCode(null);
        resident.setDeathPlaceAddress(null);
        residentRepository.save(resident);
        birthDeathRepository.deleteById(new BirthDeathReportResident.Pk(target, type));
    }
    public BirthDeathReportResident getBirth(Integer target){
        BirthDeathReportResident.Pk pk = new BirthDeathReportResident.Pk(target, BirthDeathCode.출생);
        BirthDeathReportResident birthDeathReportResident = birthDeathRepository.findById(pk).orElseThrow();
        if(Objects.isNull(birthDeathReportResident)){
            throw new SomethingNotFoundException("해당하는 주민이 존재하지 않습니다.");
        }
        return birthDeathReportResident;
    }
    public BirthDeathReportResident getDeath(Integer target){
        BirthDeathReportResident.Pk pk = new BirthDeathReportResident.Pk(target, BirthDeathCode.사망);
        BirthDeathReportResident birthDeathReportResident = birthDeathRepository.findById(pk).orElseThrow();
        if(Objects.isNull(birthDeathReportResident)){
            throw new SomethingNotFoundException("해당하는 주민이 존재하지 않습니다.");
        }
        return birthDeathReportResident;
    }
}
