package org.liu.mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

/**
 * @Author lzs
 * @Date 2022/11/19 16:21
 **/
@ExtendWith(MockitoExtension.class)
public class Mockito2Test {

    @Mock
    private ComputerService computerService;

    @Test
    public void test(){
        computerService.run();
        verify(computerService).run();
    }

}
