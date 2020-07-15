package com.test.study;

import com.google.common.base.Optional;
import com.test.study.util.arrays.ListTest;

import org.hibernate.annotations.CollectionId;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestApp {

	ArrayList<String> list = new ArrayList<>();

	@Test
	public void test4() {
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

	@Test
	public void test3() {
		String[] ss = { "11", "22" };
		for (String s : ss) {
			System.out.println(s);
		}
	}

	@Test
	public void test2() {
		int[] nums = { 1, 3, 4, 5 };
		String string = Arrays.toString(nums);
		System.out.println(string);
	}

	@Test
	public void parseLocalDate(String s) {
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.parse(s, dtf);
			System.out.println(localDate);
		} catch (Exception e) {
			throw e;
		}
	}

	@Test
	public void name() {
		ListTest listTest = new ListTest();

		list.add("e");
		listTest.setList(list);
		listTest.test2();
		for (String s : list) {
			System.out.println(s);

		}
	}

	@Test
	public void test1() {
		Object d = null;
		String s = (String) d;
		System.out.println(s);
	}

	@Test
	public void test6() {
		String xlx = "1100003272_fc";
		System.out.println(xlx.split("\\.")[0]);
		System.out.println(xlx.substring(0, xlx.lastIndexOf(".")));
	}


	@Test
	public void  test7(){
		List<String> list=new ArrayList<>();
		list.add("1");
		list.add("2");

/*		for (int i=0;i<list.size();i++){
			if ("2".equals(list.get(i))){
				list.remove(list.get(i));
			}
		}*/


		//foreach 中不能使用 【add/remove】 操作
/*		for (String s:list) {
			if ("2".equals(s)){
				list.remove(s);
			}
		}*/

		System.out.println(list);
	}

	@Test
	public void test01(){

	}

}