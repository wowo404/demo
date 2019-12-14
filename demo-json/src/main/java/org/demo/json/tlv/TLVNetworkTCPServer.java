package org.demo.json.tlv;

import lombok.Data;

import java.io.Serializable;

/**
 * TCP server
 */
@Data
public class TLVNetworkTCPServer implements Serializable {
    //0x54接口返回
    private String ip;
    private String port;
    private String domainName;//域名
}
