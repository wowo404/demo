package org.demo.json.tlv;

import lombok.Data;

import java.io.Serializable;

@Data
public class TLVModel implements Serializable {
    private Byte typeByte;
    private String typeHex;
    private Integer length;
    private String lengthHex;
    private byte[] valueBytes;
    private String valueHex;
}
