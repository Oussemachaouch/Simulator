package com.Vermeg.entities;


import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class MortalityTableAges implements Serializable{
	
	
	private int age;
	private String name;
	
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public MortalityTableAges() {
		super();
	}
	public MortalityTableAges(int age) {
		super();
		this.age = age;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}
