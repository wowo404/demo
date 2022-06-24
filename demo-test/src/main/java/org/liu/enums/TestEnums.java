package org.liu.enums;

/**
 * @author liuzhangsheng
 * @create 2019/4/29
 */
public class TestEnums {

    public static void main(String[] args) {
        getConstants();
        System.out.println(SelectionEnum.class.isAssignableFrom(AnimalType.class));
    }

    private static void getConstants() {
        AnimalType[] enumConstants = AnimalType.class.getEnumConstants();
        for (AnimalType enumConstant : enumConstants) {
            System.out.println(SelectionEnum.class.isAssignableFrom(enumConstant.getClass()));
            System.out.println(enumConstant);
            SelectionEnum selectionEnum = enumConstant;
            System.out.println(selectionEnum.code());
        }
    }

}
