package com.nhnacademy.townoffice.repository.family;

import com.nhnacademy.townoffice.entity.FamilyRelationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FamilyRelationshipRepository extends FamilyRelationshipRepositoryCustom,JpaRepository<FamilyRelationship, FamilyRelationship.Pk> {
    @Query("select f from FamilyRelationship f where f.pk.baseResidentSerialNumber= :baseResidentSerialNumber")
    List<FamilyRelationship> findAllByPk_BaseResidentSerialNumber(@Param("baseResidentSerialNumber") Integer baseResidentSerialNumber);
}
