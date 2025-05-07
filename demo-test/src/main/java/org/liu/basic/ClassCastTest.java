package org.liu.basic;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;

import java.util.Collection;

/**
 * @Author lzs
 * @Date 2022/6/24 9:47
 **/
public class ClassCastTest {

    public static void main(String[] args) {
        a();
    }

    public static void a() {
        ClassCastInnerModel innerModel = new ClassCastInnerModel();
        innerModel.setId(1L);
        innerModel.setName("a");

        ClassCastModel model = new ClassCastModel();
        model.setId(1L);
        model.setModels(new ClassCastInnerModel[]{innerModel});
        model.setModelList(CollUtil.newArrayList(innerModel));

        Object models = BeanUtil.getFieldValue(model, "models");
        //转成object数组
        Object[] objects = (Object[]) models;
        System.out.println(StrUtil.join(",", objects));

        Object modelList = BeanUtil.getFieldValue(model, "modelList");
        //转成Collection
        Collection<Object> collection = (Collection<Object>) modelList;
        System.out.println(StrUtil.join(",", collection));
    }

}
