package org.demo.json;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class CommandDTO<T> implements Serializable {
    private Integer gatewayType;//网关类型（1 - DT-FA6，2 - DT-AL8）
    private Long gatewayId;//网关ID
    private String gatewaySerialNum;//网关序列号
    private Date sendTime;//发送时间
    private T data;
}