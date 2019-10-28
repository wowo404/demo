package org.liu.nio;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class NIOFileVisitor {

    public static void main(String[] args) throws IOException {
        FileVisitor<Path> myFileVisitor = new SimpleFileVisitor<Path>(){
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println("I'm about to visit the "+dir+" directory");
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println("I'm visiting file "+file+" which has size " +attrs.size());
                return FileVisitResult.CONTINUE;
            }
        };
        Path headDir = Paths.get("D:\\funny\\doc");
        Files.walkFileTree(headDir, myFileVisitor);
    }

}
