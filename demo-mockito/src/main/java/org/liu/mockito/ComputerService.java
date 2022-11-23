package org.liu.mockito;

import java.util.List;

/**
 * @Author lzs
 * @Date 2022/11/19 10:58
 **/
public interface ComputerService {
    String combine(String computerCase, String cpu, String mainboard, String power);
    String run();
    List<String> show(Integer times);

    String broken();
}