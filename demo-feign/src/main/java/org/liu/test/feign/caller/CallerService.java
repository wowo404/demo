package org.liu.test.feign.caller;

import feign.Feign;
import feign.Request;
import feign.Retryer;
import feign.form.FormEncoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.justing.commons.model.Response;
import org.liu.test.feign.client.FileService;
import org.liu.test.feign.client.RemoteService;
import org.liu.test.feign.model.OperateAccountReq;

import java.io.File;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

public class CallerService {

    /**
     * 教程：https://www.jianshu.com/p/3d597e9d2d67/
     */
    public void callRemote(){
        RemoteService service = Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .options(new Request.Options(1000, TimeUnit.MILLISECONDS, 3500, TimeUnit.MILLISECONDS, true))
                .retryer(new Retryer.Default(5000, 5000, 3))
                .target(RemoteService.class, "http://127.0.0.1:7413");

        OperateAccountReq req = new OperateAccountReq();
        req.setUserId(1L);
        req.setAmount(new BigDecimal("102.356"));
        Response<Void> resp = service.operateAccount(req);
        System.out.println("resultCode:" + resp.getCode());
    }

    public void callFileService(){
        FileService fileService = Feign.builder().encoder(new FormEncoder()).target(FileService.class, "http://127.0.0.1:7414");

        Response<Void> resp = fileService.upload(new File("D:\\data\\photos\\gen.jpg"));
        System.out.println("resultCode:" + resp.getCode());
    }

}
