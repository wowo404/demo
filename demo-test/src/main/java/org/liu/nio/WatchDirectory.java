package org.liu.nio;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class WatchDirectory {

    public static void main(String[] args) throws IOException, InterruptedException {
        WatchService watchService = FileSystems.getDefault().newWatchService();

        File watchDirectory = new File("D:\\home\\temp");
        Path path = watchDirectory.toPath();

        WatchKey watchKey = path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);

        WatchKey take = watchService.take();
        //使用take或者watchKey来轮询都可以
        for (WatchEvent<?> event : watchKey.pollEvents()) {
            System.out.println(
                    "An event was found after file creation of kind " + event.kind()
                            + ". The event occurred on file " + event.context() + ".");
        }
    }

}
