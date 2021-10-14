package org.liu.test.feign.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OperateAccountReq {
    private Long userId;
    private BigDecimal amount;
}
