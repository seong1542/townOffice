package com.nhnacademy.townoffice.repository;

import com.nhnacademy.townoffice.entity.Household;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HouseholdRepository extends JpaRepository<Household, Integer> {
    @Query(value = "select h from Household h")
    List<Household> findAll();
}
