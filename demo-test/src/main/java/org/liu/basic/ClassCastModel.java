package org.liu.basic;

import lombok.Data;

import java.util.List;

/**
 * @Author lzs
 * @Date 2022/6/24 10:38
 **/
@Data
public class ClassCastModel {
    private Long id;
    private ClassCastInnerModel[] models;
    private List<ClassCastInnerModel> modelList;
}
