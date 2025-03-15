package org.liu.mockito;

import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class ComputerServiceTest {

    @org.junit.jupiter.api.Test
    void combine() {
        ComputerService computerService = Mockito.mock(ComputerService.class);
        Mockito.when(computerService.combine("50 * 50 * 50cm", "inter i7", "qicaihong", "greatWall")).thenReturn("success");
        System.out.println(computerService.combine("50 * 50 * 50cm", "inter i7", "qicaihong", "greatWall"));
    }
}