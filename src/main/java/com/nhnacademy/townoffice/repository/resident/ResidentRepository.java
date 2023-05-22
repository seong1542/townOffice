package com.nhnacademy.townoffice.repository.resident;

import com.nhnacademy.townoffice.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ResidentRepository extends ResidentRepositoryCustom,JpaRepository<Resident,Integer> {
    Resident findResidentByResidentSerialNumber(Integer residentSerialNumber);


    @Query("select count (*) from Household h inner join HouseholdCompositionResident c "
            +"on h.householdSerialNumber = c.pk.householdSerialNumber "
            +"where h.householdResident.residentSerialNumber= :residentSerialNumber")
    int existsResidentFindByHousehold(@Param("residentSerialNumber") Integer residentSerialNumber);
}
