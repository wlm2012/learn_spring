package com.test.study.entity;

public class BookFactory {

	public BookFactory(Book book){
		System.out.println(book.getId());
		System.out.println(book.getTitle());
	}
}
