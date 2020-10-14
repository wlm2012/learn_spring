package com.test.study.service;


import com.test.study.entity.Book;
import com.test.study.entity.BookFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class BeanService {

	@Bean
	public Book newBook() {
		Book book = new Book();
		book.setId(110L);
		book.setTitle("120");
		return book;
	}

	@Bean
	public BookFactory newBookFactory(Book book) {
		return new BookFactory(book);
	}
}
