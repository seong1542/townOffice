package com.nhnacademy.townoffice.repository.houseMove;

import com.nhnacademy.townoffice.domain.dto.HouseholdMovementDto;
import com.nhnacademy.townoffice.entity.HouseholdMovementAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

public interface HouseholdMovementRepository extends HouseholdMovementRepositoryCustom,JpaRepository<HouseholdMovementAddress, HouseholdMovementAddress.Pk> {


    @Modifying
    @Query("update HouseholdMovementAddress h set h.houseMovementAddress = :houseMovementAddress where h.pk.householdSerialNumber = :householdSerialNumber and h.pk.houseMovementDate = :houseMovementDate")
    void updateHouseMovementAddress(@Param("householdSerialNumber")Integer householdSerialNumber, @Param("houseMovementDate") LocalDate houseMovementDate, @Param("houseMovementAddress") String houseMovementAddress);

    @Query("select h.pk.houseMovementDate,h.pk.householdSerialNumber from HouseholdMovementAddress h where h.pk.householdSerialNumber = :householdSerialNumber")
    List<HouseholdMovementDto> getHouseholdMovementDtoById(@Param("householdSerialNumber") Integer householdSerialNumber);

    @Query("select h from HouseholdMovementAddress h where h.pk.householdSerialNumber = :householdSerialNumber order by h.pk.houseMovementDate DESC ")
    List<HouseholdMovementAddress> findAllByHousehold_HouseholdSerialNumberOrderByPk_HouseMovementDateDesc(@Param("householdSerialNumber") Integer householdSerialNumber);
}
