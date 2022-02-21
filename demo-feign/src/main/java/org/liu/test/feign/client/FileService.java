package org.liu.test.feign.client;

import feign.Headers;
import feign.RequestLine;
import org.justing.commons.model.Response;

import java.io.File;

/**
 * @Author lzs
 * @Date 2022/2/18 16:20
 **/
public interface FileService {

    @Headers({"Content-Type: multipart/form-data"})
    @RequestLine("POST /file/upload")
    Response<Void> upload(File file);

}