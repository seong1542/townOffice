package com.nhnacademy.townoffice.domain.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressDto {
    private String currentAddress;

    @QueryProjection
    public AddressDto(String currentAddress){
        this.currentAddress= currentAddress;
    }
}
