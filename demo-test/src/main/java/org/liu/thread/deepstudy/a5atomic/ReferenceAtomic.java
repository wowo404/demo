package org.liu.thread.deepstudy.a5atomic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;

public class ReferenceAtomic {

    public static void main(String[] args) {
        User user = new User("liu", 18);

        AtomicReference<User> atomicReference = new AtomicReference<User>(user);
        atomicReference.compareAndSet(user, new User("zhang", 28));

        System.out.println("原子更新结果：" + atomicReference.get());

        System.out.println("原始类：" + user);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    static class User {
        private String name;
        private int age;
    }

}
