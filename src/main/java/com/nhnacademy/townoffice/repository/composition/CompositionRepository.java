package com.nhnacademy.townoffice.repository.composition;

import com.nhnacademy.townoffice.domain.dto.HouseholdCompositionResidentDto;
import com.nhnacademy.townoffice.entity.HouseholdCompositionResident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompositionRepository extends CompositionRepositoryCustom,JpaRepository<HouseholdCompositionResident, HouseholdCompositionResident.Pk> {
}
