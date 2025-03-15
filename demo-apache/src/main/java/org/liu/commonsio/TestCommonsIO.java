package org.liu.commonsio;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class TestCommonsIO {

    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = FileUtils.openInputStream(new File("D:\\funny\\Master.png"));
        FileUtils.copyInputStreamToFile(fileInputStream, new File("D:\\funny\\Master1.png"));
    }

}
