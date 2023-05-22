package com.nhnacademy.townoffice.repository.family;

import com.nhnacademy.townoffice.domain.dto.ParentsDto;
import com.nhnacademy.townoffice.domain.enumType.FamilyType;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface FamilyRelationshipRepositoryCustom {
    ParentsDto getBySerialNumberAndFamilyCode(Integer target, FamilyType familyType);
}
