package org.demo.json.tlv;

import lombok.Data;

@Data
public class TLVUpgradeCache {
    private String filePath;//文件路径
    private Long fileSize;//文件大小
    private Integer packageNum;//一个字节的包号，每次升级过程中从0累加
    private Long readLength;//已读长度
}
