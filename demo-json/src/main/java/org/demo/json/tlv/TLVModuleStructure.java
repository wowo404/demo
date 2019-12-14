package org.demo.json.tlv;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 采集模块结构
 */
@Data
public class TLVModuleStructure implements Serializable {
    private Integer moduleTotalNum;//通道总数
    private Integer moduleTypeNum;//通道类型数
    private List<ModuleTypeAndNum> typeAndNumList;//通道类型+个数

    @Data
    public static class ModuleTypeAndNum implements Serializable {
        private Integer type;//通道类型
        private Integer num;//个数
    }

}
