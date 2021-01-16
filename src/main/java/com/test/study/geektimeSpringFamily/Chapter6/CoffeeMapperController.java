package com.test.study.geektimeSpringFamily.Chapter6;


import com.test.study.entity.CoffeePlus;
import com.test.study.primaryMapper.CoffeeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/coffee")
@Slf4j
public class CoffeeMapperController {


	private CoffeeMapper coffeeMapper;

	@Autowired
	public void setCoffeeMapper(CoffeeMapper coffeeMapper) {
		this.coffeeMapper = coffeeMapper;
	}

	@PostMapping(path = "/", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public CoffeePlus addCoffee(@Valid CoffeePlus coffeePlus, BindingResult result) {
		if (result.hasErrors()) {
			log.warn("erroes :{}", result);
		}

		coffeePlus.setCreatTime(LocalDateTime.now());
		coffeePlus.setUpdateTime(LocalDateTime.now());
		if (1 == coffeeMapper.insert(coffeePlus)) {
			log.info("success");
		}
		return coffeePlus;
	}


}
