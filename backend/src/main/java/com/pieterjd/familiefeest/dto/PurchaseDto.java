package com.pieterjd.familiefeest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseDto {
    private String eventCode;
    private Long eventItemId;
    private String beneficiary;

}
