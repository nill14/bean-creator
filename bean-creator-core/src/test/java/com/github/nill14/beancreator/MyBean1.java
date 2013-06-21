package com.github.nill14.beancreator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MyBean1 {

	private static final Logger log = LoggerFactory.getLogger(MyBean1.class);
	
	/**
	 * Javadoc comment
	 */
	private String name = "Hello";
	/*
	 * Non-javadoc comment
	 */
	private int age = 35;
	/**
	 * This value is fake
	 */
	private double weight = 90.0;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
}
