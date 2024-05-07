package com.green.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.green.entity.Person;

@Controller
public class ExampleController {

	@GetMapping("/thymeleaf/example")
	public ModelAndView thymeleafExample() {
		
		Person person =new Person();
		ModelAndView mv = new ModelAndView();
		
		person.setId(1L);
		person.setName("홍길동");
		person.setAge(17);
		person.setHobbies(List.of("축구","야구"));
		
		mv.addObject("time",LocalDate.now());
		mv.addObject("person",person);
		mv.setViewName("example");
		
		return mv;
		
	}
	
}
