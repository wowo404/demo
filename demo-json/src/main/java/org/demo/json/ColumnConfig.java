package org.demo.json;

import lombok.Data;

/**
 * @Author lzs
 * @Date 2022/11/10 14:54
 **/
@Data
public class ColumnConfig {
    private String name;
    private Object table;
    private String nfmt;
    private String type;
    private String flag;
    private String[] major;
    private Object exp;
}
