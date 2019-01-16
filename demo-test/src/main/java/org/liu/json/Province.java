package org.liu.json;

import lombok.Data;

import java.util.List;

/**
 * @author liuzhangsheng
 * @create 2018/10/30
 */
@Data
public class Province {
    private String name;
    private List<City> cityList;

    @Data
    public class City {
        private String name;
    }
}
