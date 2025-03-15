package org.liu.demo.mybatis;

import lombok.Data;

import java.util.List;

/**
 * @author lzs
 * @Date 2024/5/27 10:25
 **/
@Data
public class Page<T> {
    private Long current;
    private Long size;
    private Long totalPages;
    private Long totalSizes;
    private List<T> data;
}
