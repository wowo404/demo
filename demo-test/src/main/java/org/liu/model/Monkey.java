package org.liu.model;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.List;

@Entity
public class Monkey extends Animal implements Action, Serializable {

	private String color;

	private List<MonkeySons> sons;

	private MonkeySons oldSon;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

    public List<MonkeySons> getSons() {
        return sons;
    }

    public void setSons(List<MonkeySons> sons) {
        this.sons = sons;
    }

	public MonkeySons getOldSon() {
		return oldSon;
	}

	public void setOldSon(MonkeySons oldSon) {
		this.oldSon = oldSon;
	}

	@Override
	public void run() {
		System.out.println("Monkey climb tree");
	}

	public OriginFormTypeEnum getFormType(String id, List<SysDept> depts){
		System.out.println(id);
		System.out.println(depts);
		return OriginFormTypeEnum.OTHER_STORAGE;
	}
}
