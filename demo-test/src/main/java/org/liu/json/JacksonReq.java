package org.liu.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author liuzhangsheng
 * @create 2018/10/24
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class JacksonReq {
    private String url;
}
