package org.demo.json.tlv;

import lombok.Data;

import java.io.Serializable;

/**
 * DNS
 */
@Data
public class TLVNetworkDNS implements Serializable {
    //0x59返回
    private String dns;
}
