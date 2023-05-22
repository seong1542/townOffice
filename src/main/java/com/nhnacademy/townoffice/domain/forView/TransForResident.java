package com.nhnacademy.townoffice.domain.forView;

import com.nhnacademy.townoffice.domain.enumType.FamilyType;
import com.nhnacademy.townoffice.domain.enumType.Gender;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TransForResident {
    private FamilyType familyRelationshipCode;
    private String name;
    private String birthDate;
    private String residentRegistrationNumber;
    private Gender genderCode;

    public TransForResident(FamilyType familyRelationshipCode, String name, String birthDate, String residentRegistrationNumber, Gender genderCode ){
        this.familyRelationshipCode = familyRelationshipCode;
        this.name = name;
        this.birthDate = birthDate;
        this.residentRegistrationNumber = residentRegistrationNumber;
        this.genderCode = genderCode;
    }
}
