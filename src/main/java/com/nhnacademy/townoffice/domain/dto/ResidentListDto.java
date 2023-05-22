package com.nhnacademy.townoffice.domain.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResidentListDto {
    private Integer residentSerialNumber;
    private String name;

    private boolean isBirthExist;
    private boolean isDeathExist;

    @QueryProjection
    public ResidentListDto(Integer residentSerialNumber, String name, boolean isBirthExist, boolean isDeathExist){
        this.residentSerialNumber = residentSerialNumber;
        this.name = name;
        this.isBirthExist = isBirthExist;
        this.isDeathExist = isDeathExist;
    }
}
