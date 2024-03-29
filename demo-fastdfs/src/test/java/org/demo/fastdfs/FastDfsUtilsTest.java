package org.demo.fastdfs;

import org.csource.common.MyException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class FastDfsUtilsTest {

    @org.junit.jupiter.api.Test
    void upload() throws IOException, MyException {
        String upload = FastDfsUtils.upload("E:\\data\\photos\\a.jpeg", "tag1", "餐桌");
        System.out.println(upload);
    }

    @Test
    void delete() throws IOException, MyException {
        FastDfsUtils.delete("group1/M00/00/00/wKgAMGYGhsuAVyk4AAB0JWPy5HU57.jpeg");
    }

    @Test
    void get() throws IOException, MyException {
        FastDfsUtils.get("group1/M00/00/00/wKgAMGYGhsuAVyk4AAB0JWPy5HU57.jpeg");
    }

    @Test
    void metadata() throws IOException, MyException {
        FastDfsUtils.metadata("group1/M00/00/00/wKgAMGYGhsuAVyk4AAB0JWPy5HU57.jpeg");
    }

    @Test
    void download() throws IOException, MyException {
        FastDfsUtils.download("group1/M00/00/00/wKgAMGYGhsuAVyk4AAB0JWPy5HU57.jpeg", "E:\\downloads\\a.jpeg");
    }
}