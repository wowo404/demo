package org.liu.thread.deepstudy.a7threadsafequeue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Message implements Delayed {

    private String title;
    private String msg;
    private long delay;

    public Message(String title, String msg, long delay, TimeUnit unit) {
        this.title = title;
        this.msg = msg;
        this.delay = System.currentTimeMillis() + unit.toMillis(delay);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return delay - System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed o) {
        Message m = (Message) o;
        long interval = this.delay - m.delay;
        if (interval == 0) {
            return 0;
        } else if (interval < 0) {
            return -1;
        }
        return 1;
    }

    public String toString() {
        return title + ": " + msg + " (delay: " + delay + ")";
    }
}
