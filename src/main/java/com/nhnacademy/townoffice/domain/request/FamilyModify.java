package com.nhnacademy.townoffice.domain.request;

import com.nhnacademy.townoffice.domain.enumType.FamilyType;
import lombok.Data;

@Data
public class FamilyModify {
    private FamilyType familyRelationshipCode;
}
