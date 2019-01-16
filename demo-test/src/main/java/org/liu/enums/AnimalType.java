package org.liu.enums;

/**
 * Created by hello on 2017/11/9.
 */
public enum AnimalType {
    FLY(0,"飞行动物"),LAND(1,"陆地动物"),SEA(2,"海洋动物");
    public Integer type;
    public String desc;
    AnimalType(Integer type, String desc){
        this.type = type;
        this.desc = desc;
    }
    public static AnimalType create(Integer type){
        AnimalType[] arr = AnimalType.values();
        for (AnimalType animalType : arr) {
            if(animalType.type.equals(type)){
                return animalType;
            }
        }
        return null;
    }
}
