package org.demo.json.tlv;

import lombok.Data;

import java.io.Serializable;

/**
 * 通道相关配置
 */
@Data
public class TLVTunnelSettings implements Serializable {
    private Integer tunnelCode;//通道号
    private Integer samplingRate;//采样率（4个字节）
    private Float sensitivity;//灵敏度（4个字节）
    private Float alarmValue;//报警值（4个字节）
    private Float range;//量程（4个字节）
}
