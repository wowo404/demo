package org.liu.mockito;

import java.util.Arrays;
import java.util.List;

/**
 * @Author lzs
 * @Date 2022/11/19 10:59
 **/
public class ComputerServiceImpl implements ComputerService {

    private ComputerDao computerDao;

    @Override
    public String combine(String computerCase, String cpu, String mainboard, String power) {
        return computerCase + cpu + mainboard + power;
    }

    @Override
    public String run() {
        return "Bravo";
    }

    @Override
    public List<String> show(Integer times) {
        return Arrays.asList("the good", "the bad", "the ugly");
    }

    @Override
    public String broken() {
        return computerDao.makeSound();
    }

    public ComputerDao getComputerDao() {
        return computerDao;
    }

    public void setComputerDao(ComputerDao computerDao) {
        this.computerDao = computerDao;
    }
}
