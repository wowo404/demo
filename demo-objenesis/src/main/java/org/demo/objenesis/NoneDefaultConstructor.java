package org.demo.objenesis;

public class NoneDefaultConstructor {

    private Integer id;
    private String name;

    public NoneDefaultConstructor(Integer id, String name){
        this.id = id;
        this.name = name;
    }

    public void run(){
        System.out.println("i don't have default constructor");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
