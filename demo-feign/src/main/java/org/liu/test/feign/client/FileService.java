package org.liu.test.feign.client;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.form.FormData;
import feign.form.FormProperty;
import lombok.Getter;
import lombok.Setter;
import org.justing.commons.model.Response;

import java.io.File;

/**
 * @Author lzs
 * @Date 2022/2/18 16:20
 **/
public interface FileService {

    @Headers({"Content-Type: multipart/form-data"})
    @RequestLine("POST /file/upload")
    Response<Void> upload(@Param("file") File file);

    //参数可以采用多种形式
    @Headers({"Content-Type: multipart/form-data"})
    @RequestLine("POST /file/upload")
    Response<Void> upload(@Param("file") byte[] file);

    @Headers({"Content-Type: multipart/form-data"})
    @RequestLine("POST /file/upload")
    Response<Void> upload(@Param("file") FormData file);

    @Headers({"Content-Type: multipart/form-data"})
    @RequestLine("POST /file/upload")
    Response<Void> upload(CustomFilePojo file);

    @Getter
    @Setter
    class CustomFilePojo {
        @FormProperty("isPublic")
        Boolean isPublic;
        File file;
    }

}