package org.liu.mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * @Author lzs
 * @Date 2022/11/19 17:52
 **/
@ExtendWith(MockitoExtension.class)
public class MockitoSpyTest {

    @Spy
    private ComputerService computerService;

    @Test
    public void test(){
        computerService.show(1);
        when(computerService.show(1)).thenReturn(Arrays.asList("1", "2"));

        verify(computerService).show(1);
    }

    @Test
    public void spyList() {
        //申请了一个真实的对象
        List list = new LinkedList();
        List spy = spy(list);

        //可以选择存根某些函数
        when(spy.size()).thenReturn(100);

        //调用真实的方法
        spy.add("one");
        spy.add("two");

        //打印第一个元素
        System.out.println(spy.get(0));

        //获取list的大小
        System.out.println(spy.size());

        //验证
        verify(spy).add("one");
        verify(spy).add("two");
    }

    @Test
    public void spyList1() {
        List list = new LinkedList();
        List spy = spy(list);

        //报错 IndexOutOfBoundsException， 因为这个List还是empty
        when(spy.get(0)).thenReturn("foo");
        //通过
        doReturn("foo").when(spy).get(0);
    }

}
