package org.demo.wechat.mp.dto;

import lombok.Data;

/**
 * 微信验证接口req
 *
 * @author liuzhangsheng
 * @create 2019/6/26
 */
@Data
public class WechatValidateReq {
    private String signature;
    private String timestamp;
    private String nonce;
    private String echostr;
}
