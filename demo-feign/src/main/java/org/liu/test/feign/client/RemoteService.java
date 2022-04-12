package org.liu.test.feign.client;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.justing.commons.model.Response;
import org.liu.test.feign.model.OperateAccountReq;

public interface RemoteService {

    @RequestLine("GET /user/getByName?name={name}")
    String getByName(@Param(value = "name") String name);

    @Headers({"Content-Type: application/json", "Accept: application/json", "Authorization: {token}"})
    @RequestLine("POST /user/operateAccount")
    Response<Void> operateAccount(@Param("token") String token, OperateAccountReq req);

}
