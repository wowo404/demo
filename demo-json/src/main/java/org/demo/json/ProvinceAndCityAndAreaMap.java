package org.demo.json;

import lombok.Data;

import java.util.Map;

/**
 * @author liuzhangsheng
 * @create 2018/10/30
 */
@Data
public class ProvinceAndCityAndAreaMap {

    private String name;
    private Map<String, CityInner> child;

}
