package org.liu.demo.junit5;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author lzs
 * @Date 2024/7/25 17:55
 **/
public class ConcurrentTest {

    @Execution(ExecutionMode.CONCURRENT)
    @TestFactory
    Collection<DynamicTest> dynamicTest() {
        DynamicTest dynamicTest1 = DynamicTest.dynamicTest("dynamicTest1", () -> {
            Thread.sleep(1000L);
            System.out.println(Thread.currentThread().getName() + "---1st parallel");
        });

        DynamicTest dynamicTest2 = DynamicTest.dynamicTest("dynamicTest2", () -> {
            Thread.sleep(1000L);
            System.out.println(Thread.currentThread().getName() + "---2st parallel");
        });

        DynamicTest dynamicTest3 = DynamicTest.dynamicTest("dynamicTest3", () -> {
            Thread.sleep(1000L);
            System.out.println(Thread.currentThread().getName() + "---3st parallel");
        });
        return Arrays.asList(dynamicTest1, dynamicTest2, dynamicTest3);
    }

}
