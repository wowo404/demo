package org.demo.json.tlv;

import lombok.Data;

import java.io.Serializable;

/**
 * 特征值
 */
@Data
public class TLVEigenvalue implements Serializable {
    private Integer tunnelCode;//通道号
    private Integer eigenvalueNum;//特征值个数
    private Float[] eigenvalueArray;//特征值
    private Float speedValue;//速率有效值，12个特征值中的最后一个
}
