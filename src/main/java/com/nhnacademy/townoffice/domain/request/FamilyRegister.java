package com.nhnacademy.townoffice.domain.request;

import com.nhnacademy.townoffice.domain.enumType.FamilyType;
import lombok.Data;
import lombok.Getter;

@Data
public class FamilyRegister {

    private Integer familyResidentSerialNumber;
    private FamilyType familyRelationshipCode;
}
