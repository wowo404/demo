package org.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

@Slf4j
@Service
public class TestService {

    private Queue<String> queue = new LinkedBlockingDeque<>(20);

    @PostConstruct
    public void init(){
        Runtime.getRuntime().addShutdownHook(new GracefulShutdownHookThread());
    }

    public class GracefulShutdownHookThread extends Thread {
        @Override
        public void run() {
            save();
        }
    }

    private void save() {
        log.info("analysis message, total size is {}", queue.size());
    }

    public void analysis(String msg) {
        queue.add(msg);
    }
}
