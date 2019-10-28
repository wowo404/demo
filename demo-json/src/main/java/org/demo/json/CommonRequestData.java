package org.demo.json;

import lombok.Getter;
import lombok.Setter;

/**
 * 通用业务参数request
 *
 * @author liuzhangsheng
 * @create 2018/8/26
 */
@Getter
@Setter
public class CommonRequestData {

    private String requestNo;
    private String appId;
    private String digest;
    private String redirectUrl;
    private String notifyUrl;

}
