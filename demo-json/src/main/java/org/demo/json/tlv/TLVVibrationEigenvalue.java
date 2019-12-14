package org.demo.json.tlv;

import lombok.Data;

import java.io.Serializable;

@Data
public class TLVVibrationEigenvalue implements Serializable {
    private Integer variety;//特征值种类
    private Float alarmValue;//特征值报警值
}
