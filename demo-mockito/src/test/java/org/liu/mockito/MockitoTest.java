package org.liu.mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

/**
 * @Author lzs
 * @Date 2022/11/19 14:35
 **/
@ExtendWith(MockitoExtension.class)
public class MockitoTest {

    @Test
    public void test() {
        ComputerService computerService = mock(ComputerService.class);
        when(computerService.run()).thenReturn("go go go", "woo woo");
        System.out.println(computerService.run());
        System.out.println(computerService.run());
    }

    @Test
    public void test1() {
        ComputerService service = mock(ComputerService.class);
        doThrow(new RuntimeException()).when(service).show(1);
        service.show(1);
    }

    @Test
    public void mockList() {
        List mockedList = mock(List.class);
        when(mockedList.get(anyInt())).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                System.out.println("哈哈哈，被我逮到了吧");
                Object[] arguments = invocationOnMock.getArguments();
                System.out.println("参数为:" + Arrays.toString(arguments));
                Method method = invocationOnMock.getMethod();
                System.out.println("方法名为:" + method.getName());

                return "结果由我决定";
            }
        });

        System.out.println(mockedList.get(0));
    }

    @Test
    public void test2() {
        List mockedList = mock(List.class);

        when(mockedList.get(0)).thenReturn("first");

        //返回first
        System.out.println(mockedList.get(0));

        //验证是否调用过get函数。这里的anyInt()就是一个参数匹配器。
        verify(mockedList).get(anyInt());
    }

    @Test
    public void test3(){
        ComputerService service = mock(ComputerService.class);
        when(service.combine("50 * 50 * 50cm", "inter i7", "qicaihong", "greatWall")).thenReturn("ok");

        System.out.println(service.combine("50 * 50 * 50cm", "inter i7", "qicaihong", "greatWall"));

        verify(service).combine(anyString(), anyString(), anyString(), eq("greatWall"));
    }

}
