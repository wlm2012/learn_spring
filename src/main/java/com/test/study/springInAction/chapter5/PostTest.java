package com.test.study.springInAction.chapter5;


import com.test.study.entity.Teacher;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class PostTest {

	@RequestMapping(value = "/httpTest/{id}/{num}", method = RequestMethod.GET)
	public void httpTest(@PathVariable String id, @PathVariable int num, @RequestParam int i) {
		System.out.println(id);
		System.out.println(num);
		System.out.println(Integer.valueOf(id) / num);
		System.out.println(i);
	}

	@RequestMapping(value = "/httpTest", method = RequestMethod.POST)
	public void httpTest(@Valid @RequestBody Teacher teacher, Errors errors) {
		if (errors.hasErrors()) {
			System.out.println("wrong");
		}
		System.out.println(teacher);
	}
}
