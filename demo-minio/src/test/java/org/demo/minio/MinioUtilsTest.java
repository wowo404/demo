package org.demo.minio;

import org.junit.jupiter.api.Test;

class MinioUtilsTest {

    @org.junit.jupiter.api.Test
    void upload() {
        MinioUtils.upload();
    }

    @Test
    void delete() {
        MinioUtils.delete();
    }

    @Test
    void getUrl() {
        MinioUtils.getUrl();
    }

    @Test
    void put() {
        MinioUtils.put();
    }

    @Test
    void get() {
        MinioUtils.get();
    }
}