package org.liu.enums;

/**
 * @author liuzhangsheng
 * @create 2019/4/29
 */
public class TestEnums {

    public static void main(String[] args) {
        LanguageEnum abc = LanguageEnum.valueOf("abc");
        System.out.println(abc);

        for (PartsAccountExportTemplateEnum value : PartsAccountExportTemplateEnum.values()) {
            System.out.println(value);
        }
    }

}
