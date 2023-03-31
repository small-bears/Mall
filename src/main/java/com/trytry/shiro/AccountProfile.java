package com.trytry.shiro;

import lombok.Data;

import java.io.Serializable;

@Data
public class AccountProfile implements Serializable {
    private Integer customerId;
    private String username;
}

