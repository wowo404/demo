package org.liu.mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

/**
 * @Author lzs
 * @Date 2022/11/23 15:50
 **/
@ExtendWith(MockitoExtension.class)
public class InjectTest {

    @InjectMocks
    private ComputerService computerService = new ComputerServiceImpl();

    @Mock
    private ComputerDao computerDao;

    @Test
    public void test(){
        when(computerDao.makeSound()).then(invocation -> "Oops!").thenReturn("OMG!");
        System.out.println(computerService.broken());
        System.out.println(computerService.broken());
    }

}
