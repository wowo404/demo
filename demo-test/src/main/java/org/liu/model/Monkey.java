package org.liu.model;

import java.io.Serializable;
import java.util.List;

public class Monkey extends Animal implements Action, Serializable {

	private String color;

	private List<MonkeySons> sons;

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

    @Override
	public void run() {
		System.out.println("Monkey climb tree");
	}
}
