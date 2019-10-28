package org.liu.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.DosFileAttributeView;
import java.nio.file.attribute.DosFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.concurrent.TimeUnit;

public class FileAttributes {

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("D:\\funny\\南康某小区拍摄.png");
        DosFileAttributes dosFileAttributes = Files.readAttributes(path, DosFileAttributes.class);
        FileTime fileTime = dosFileAttributes.creationTime();
        long size = dosFileAttributes.size();
        System.out.println("size=" + size);
        System.out.println("creationTime=" + fileTime);
        //改变第二个参数，可以获取到设置不同文件属性的view
        DosFileAttributeView fileAttributeView = Files.getFileAttributeView(path, DosFileAttributeView.class);
        fileAttributeView.setTimes(FileTime.from(1000L, TimeUnit.SECONDS), FileTime.from(1000L, TimeUnit.SECONDS), FileTime.from(1000l, TimeUnit.SECONDS));
        fileAttributeView.setHidden(false);
    }

}
