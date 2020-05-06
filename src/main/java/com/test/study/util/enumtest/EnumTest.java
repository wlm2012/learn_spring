package com.test.study.util.enumtest;

import java.util.Scanner;

/**
 * @author wlm
 */
public class EnumTest {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("small, medium, large");
		String input = in.next();
		Size size = Enum.valueOf(Size.class, input);
		System.out.println("string=" + Size.small.toString());
		System.out.println("size=" + size);
		System.out.println("ab=" + size.getAd());
		System.out.println("s=" + size.getS());
		in.close();
	}
	/*
	 * input: small
	 * output: string=small size=small ab=s s=1
	 * 
	 */

}

enum Size {
	// ad 大小 s 数量
	small("s", "1"), medium("m", "2"), large("l", "3");

	private Size(String ad, String s) {
		this.ad = ad;
		this.s = s;
	}

	private String ad;
	private String s;

	public String getAd() {
		return ad;
	}

	public String getS() {
		return s;
	}
}