package org.demo.json;

import lombok.Data;

/**
 * @Author lzs
 * @Date 2022/7/20 10:01
 **/
@Data
public class CategoryOrPublishResp extends InfoCategory {
    /**
     * 内容
     */
    private InfoPublish infoPublish;
}
