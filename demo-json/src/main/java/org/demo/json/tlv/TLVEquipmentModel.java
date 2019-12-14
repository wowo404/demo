package org.demo.json.tlv;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 联能设备model
 * 用TLV协议传输数据
 */
@Data
public class TLVEquipmentModel implements Serializable {
    private String serialNum;//设备序列号
    private TLVModuleStructure moduleStructure;//采集模块结构
    private TLVNetworkArgs networkArgs;//网络参数
    private TLVNetworkTCPServer networkTCPServer;//tcp server相关参数
    private TLVNetworkDNS networkDNS;//dns
    private TLVSamplingRateArgs samplingRateArgs;//采样参数
    private List<TLVTunnelSettings> tunnelSettingsList;//设备通道配置：通道采样率，通道灵敏度，报警值，通道量程
    private TLVHeartbeatArgs heartbeatArgs;//心跳参数
    private List<TLVVibrationEigenvalue> vibrationEigenvalueList;//振动特征值
    private Integer workMode;//工作模式：特征值模式（0x01）、时域模式（0x02）、FFT模式（0x03）、FFT取值模式（0x04）、idle（0x05）模式
    private String tunnelWorkState;//此字段放的是8位二进制字符串，通道字：采集模块通道是否采集，默认为0xffff,即都采集
    private String softVersion;
}
