package com.test.study.util.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * @author lenovo2
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

	private String name;
	private int old;
	public String sex;
	public static String like="linux";


	public Person(String name, int old) {
		this(name, old, "1");
	}


	public void printNull() {
		System.out.println("null");
	}

	public void printName() {
		System.out.println("name:" + name);
	}

	public void printYear(String year) {
		System.out.println("year: " + year);
	}


	public static void printLike() {
		System.out.println("like:  " + like);
	}


}