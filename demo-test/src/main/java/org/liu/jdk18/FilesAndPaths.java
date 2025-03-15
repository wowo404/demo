package org.liu.jdk18;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @Author lzs
 * @Date 2023/1/10 16:53
 **/
public class FilesAndPaths {

    public static void main(String[] args) throws IOException {
        test();
    }

    public static void test() throws IOException {
        Path tempDirectory = Files.createTempDirectory("myTemp");
        System.out.println(tempDirectory.toAbsolutePath());
        System.out.println(tempDirectory.toString());
    }

}
