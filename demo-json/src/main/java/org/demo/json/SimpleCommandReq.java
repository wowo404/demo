package org.demo.json;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleCommandReq {
    private Integer dataType;//数据类型：1=字符，2=16进制
    private String cmdContent;//命令内容
}
