package com.trytry.common.dto;

import lombok.Data;

@Data
public class AliPay {
    private String subject;
    private String traceNo;
    private String totalAmount;

}
