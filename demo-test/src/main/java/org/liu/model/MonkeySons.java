package org.liu.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author liuzhangsheng
 * @create 2018/10/24
 */
public class MonkeySons {

    @JsonProperty(value = "son_name")
    private String sonName;

    public String getSonName() {
        return sonName;
    }

    public void setSonName(String sonName) {
        this.sonName = sonName;
    }
}
