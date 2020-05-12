package com.test.study;

import com.test.study.util.arrays.ListTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class Test {

	ArrayList<String> list = new ArrayList<>();

	public static void main(String[] args) {
		test6();
	}

	public static void test4() {
		int count = 123411;
		int SlicePagNum = 1000;
		int pageCount = count % SlicePagNum == 0 ? count / SlicePagNum : count / SlicePagNum + 1;
		int[] acctNos = new int[pageCount * 2];
		int pageNo = 0;
		int i;
		for (i = 0; i < acctNos.length; i += 2) {
			acctNos[i] = 1 + pageNo * SlicePagNum;
			acctNos[i + 1] = (pageNo + 1) * SlicePagNum;
			pageNo++;
		}
		acctNos[acctNos.length - 1] = count;
		String strIn = Arrays.toString(acctNos).substring(1);
		strIn = strIn.substring(0, strIn.length() - 1);
		System.out.println(strIn);
	}

	public static void test3() {
		String[] ss = {"11", "22"};
		for (String s : ss) {
			System.out.println(s);
		}
	}

	public static void test2() {
		int[] nums = {1, 3, 4, 5};
		String string = Arrays.toString(nums);
		System.out.println(string);
	}

	public static LocalDate parseLocalDate(String s) {
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.parse(s, dtf);
			return localDate;
		} catch (Exception e) {
			throw e;
		}
	}

	public void name() {
		ListTest listTest = new ListTest();

		list.add("e");
		listTest.setList(list);
		listTest.test2();
		for (String s : list) {
			System.out.println(s);

		}
	}

	public static void test1() {
		Object d = null;
		String s = (String) d;
		System.out.println(s);
	}

	public static void test6() {
		String xlx="1100003272_fc.xlsx";
		System.out.println(xlx.split("\\.")[0]);
		System.out.println(xlx.substring(0,xlx.lastIndexOf(".")));
	}

}