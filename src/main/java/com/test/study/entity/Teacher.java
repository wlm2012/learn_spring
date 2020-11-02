package com.test.study.entity;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import java.util.Date;

@Data
public class Teacher {

	private String id;
	private String name;

	@Min(0)
	@Max(100)
	private int age;

	@Past
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date bornDate;

}
