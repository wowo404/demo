package org.liu.basic;

import lombok.Data;

/**
 * @Author lzs
 * @Date 2022/6/24 10:38
 **/
@Data
public class ClassCastModel {
    private Long id;
    private ClassCastInnerModel[] models;
}
