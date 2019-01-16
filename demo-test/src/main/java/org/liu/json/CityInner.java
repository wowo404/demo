package org.liu.json;

import lombok.Data;

import java.util.Map;

/**
 * @author liuzhangsheng
 * @create 2018/10/30
 */
@Data
public class CityInner{
    private String name;
    private Map<String, String> child;
}
