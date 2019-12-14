package org.demo.json.tlv;

import lombok.Data;

import java.io.Serializable;

/**
 * 网络参数
 */
@Data
public class TLVNetworkArgs implements Serializable {
    //0x04
    private String ip;//ip
    private String mask;//子网掩码
    private String gateway;//网关
    private boolean isDHCP;//
}
