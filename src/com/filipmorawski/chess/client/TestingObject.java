package com.filipmorawski.chess.client;

import java.io.Serializable;

public class TestingObject implements Serializable {
	private String name;
	private int age;
	private String weapon;
	
	public TestingObject(String name, int age, String weapon) {
		super();
		this.name = name;
		this.age = age;
		this.weapon = weapon;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getWeapon() {
		return weapon;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setWeapon(String weapon) {
		this.weapon = weapon;
	}
}
