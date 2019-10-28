package org.liu.reflect;

import org.liu.model.MonkeySons;

import java.util.ArrayList;
import java.util.List;

public class TestBeanUtils {

    public static void main(String[] args) {
        MonkeySons monkeySons = new MonkeySons();
        monkeySons.setSonName("a");

        MonkeySons monkeySons1 = new MonkeySons();
        monkeySons1.setSonName("a");

        List<MonkeySons> sons = new ArrayList<>();
        sons.add(monkeySons);
        sons.add(monkeySons1);

        List<MonkeySons> sons2 = new ArrayList<>();

    }

}
