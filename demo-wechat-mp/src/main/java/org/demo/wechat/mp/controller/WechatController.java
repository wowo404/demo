package org.demo.wechat.mp.controller;

import org.demo.wechat.mp.dto.WechatValidateReq;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuzhangsheng
 * @create 2019/6/26
 */
@RestController
public class WechatController {

    //微信公众平台--基础配置--Token
    @Value("${wechat.token}")
    public String token;

    @GetMapping(value = "validate", produces = MediaType.TEXT_PLAIN_VALUE)
    public String validate(WechatValidateReq req){
        String temp = req.getNonce() + req.getTimestamp() + token;
        return "";
    }

}
