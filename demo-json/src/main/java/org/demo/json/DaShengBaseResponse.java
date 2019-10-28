package org.demo.json;

import lombok.Data;

/**
 * 大圣请求响应参数
 *
 * @author liuzhangsheng
 * @create 2019/1/28
 */
@Data
public class DaShengBaseResponse {

    private String message;
    private String orderNo;
    private String code;
    private Object res;

}
