package org.liu.demo.junit5;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class FirstJunit5Test {

    @BeforeEach
    @DisplayName("每条用例开始时执行")
    void start() {
        System.out.println("before test");
    }

    @AfterEach
    @DisplayName("每条用例结束时执行")
    void end() {
        System.out.println("after test");
    }

    @Test
    void firstTest() {
        assertEquals(2, 1 + 1);
    }

    @Test
    @Disabled("这条用例暂时跑不过，忽略!")
    void myFailTest() {
        assertEquals(1, 2);
    }

    @Test
    @DisplayName("运行一组断言")
    public void assertAllCase() {
        assertAll("groupAssert",
                () -> assertEquals(2, 1 + 1),
                () -> assertTrue(1 > 0)
        );
    }

    @Test
    @DisplayName("依赖注入1")
    public void testInfo(final TestInfo testInfo) {
        System.out.println(testInfo.getDisplayName());
    }

    @Test
    @DisplayName("依赖注入2")
    public void testReporter(final TestReporter testReporter) {
        testReporter.publishEntry("name", "Alex");
    }

    @RepeatedTest(3)
    public void repeatTest(){
        System.out.println("当前测试方法重复执行多次");
    }

    @Timeout(500)
    @Test
    public void timeout() throws InterruptedException {
        Thread.sleep(1000L);
    }

    @Nested
    class NestedTest{

        @BeforeEach
        @DisplayName("嵌套测试每条用例开始时执行")
        void start() {
            System.out.println("nested test before test");
        }

        @AfterEach
        @DisplayName("嵌套测试每条用例结束时执行")
        void end() {
            System.out.println("nested test after test");
        }

        @Test
        void one(){
            System.out.println("nested test one");
        }

    }

}
