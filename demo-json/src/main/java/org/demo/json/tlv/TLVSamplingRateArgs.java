package org.demo.json.tlv;

import lombok.Data;

import java.io.Serializable;

/**
 * 采样参数
 */
@Data
public class TLVSamplingRateArgs implements Serializable {
    private Integer moduleSamplingRate;//采集模块采样率（暂时用不上，以备后续）（2个字节）
    private Integer samplingCycle;//采样周期（1字节），采样周期和采样间隔无效，可以写个固定值0x00 0x01在里面
    private Integer samplingInterval;//采样间隔（2字节）
    private Integer isAutoUploadWaveform;//是否自动上传波形（1字节）
    private Integer autoUploadWaveformInterval;//自动上传波形间隔（2字节）--就是网络传输间隔
    private Integer isAutoUploadEigenvalue;//是否自动上传特征值（1字节）
}