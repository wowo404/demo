package org.demo.json;

import lombok.Data;

/**
 * @Author lzs
 * @Date 2023/9/26 9:28
 **/
@Data
public class DataProcessFormula {
    /**
     * 条件：prefix[rt]
     */
    private String condition;
    /**
     * 如何处理：remove_prefix[2]
     */
    private String how;
    /**
     * 处理后的结果：fill_in[areaNumber]
     */
    private String target;
}
