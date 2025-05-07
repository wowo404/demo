package org.liu.thread.deepstudy.a5atomic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class FieldAtomicTest {

    public static AtomicIntegerFieldUpdater<User> atomicIntegerFieldUpdater = AtomicIntegerFieldUpdater.newUpdater(User.class, "age");

    public static void main(String[] args) {
        User user = new User("liu", 18);
        System.out.println(atomicIntegerFieldUpdater.getAndIncrement(user));
        System.out.println(atomicIntegerFieldUpdater.get(user));
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class User {
        private String name;
        public volatile int age;
    }

}
