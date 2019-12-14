package org.demo.json.tlv;

import lombok.Data;

import java.io.Serializable;

/**
 * 心跳参数
 */
@Data
public class TLVHeartbeatArgs implements Serializable {
    private boolean isOpen;//是否使能心跳包
    private Integer interval;//心跳包间隔
}
