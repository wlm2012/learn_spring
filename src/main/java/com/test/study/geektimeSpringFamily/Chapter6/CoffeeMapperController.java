package com.test.study.geektimeSpringFamily.Chapter6;


import com.test.study.entity.CoffeePlus;
import com.test.study.primaryMapper.CoffeeMapper;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/coffee")
@Slf4j
public class CoffeeMapperController {


	private CoffeeMapper coffeeMapper;

	@Autowired
	public void setCoffeeMapper(CoffeeMapper coffeeMapper) {
		this.coffeeMapper = coffeeMapper;
	}

	@PostMapping(path = "/", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public CoffeePlus addCoffee(@Valid CoffeePlus coffeePlus, BindingResult result) {
		if (result.hasErrors()) {
			log.warn("erroes :{}", result);
//			int a=0/0;
			throw new FormValidationException(result);
		}

		coffeePlus.setCreateTime(LocalDateTime.now());
		coffeePlus.setUpdateTime(LocalDateTime.now());
		if (1 == coffeeMapper.insert(coffeePlus)) {
			log.info("success");
		}
		return coffeePlus;
	}


	@PostMapping(path = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public List<CoffeePlus> addCoffee(@RequestParam("file") MultipartFile file) {
		List<CoffeePlus> list = new ArrayList<>();
		if (!file.isEmpty()) {
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
				String str;
				while ((str = reader.readLine()) != null) {

//					StringUtils.split()
					String[] arr = str.split(" ");
					if (arr.length == 2) {
						CoffeePlus coffeePlus = CoffeePlus.builder().name(arr[0]).price(new BigDecimal(arr[1])).build();
						coffeeMapper.insert(coffeePlus);
						list.add(coffeePlus);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return list;
	}


	@PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public CoffeePlus addJsonCoffee(@Valid CoffeePlus coffeePlus, BindingResult result) {
		if (result.hasErrors()) {
			log.warn("erroes : {}", result);
			throw new ValidationException(result.toString());
		}
		coffeeMapper.insert(coffeePlus);
		return coffeePlus;
	}

	@ExceptionHandler(ValidationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Map<String, String> validationExceptionHandler(ValidationException exception) {
		Map<String, String> map = new HashMap<>();
		map.put("message", "????");
		return map;
	}


}
