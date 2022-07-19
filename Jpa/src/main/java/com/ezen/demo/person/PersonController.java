package com.ezen.demo.person;

import java.util.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	PersonService svc;
	@Autowired
	PersonResipatory dao;
	
	@GetMapping("/info")
	public String input(Model model) {
		model.addAttribute("person",new Person());
		System.out.println("here");
		return "thymeleaf/person/infoForm";
	}
	
	
	@PostMapping("/save")
	public String save(@Valid Person person, BindingResult res) {
		
		if(res.hasErrors()) {
			return "thymeleaf/person/infoForm";
		};
		
		
		dao.save(person);
		return "redirect:thymeleaf/person/personList";
	}
	
//	@PostMapping("/save")
//	@ResponseBody
//	public Map<String,Object> save(@Valid Person person, BindingResult res) {
//		//넘어온 데이터를 검증, 이후의 결과를 보여줌
//		if(res.hasErrors()) {
//		};//검증에 실패했다면
//		
//		return svc.save(person);
//	}
	
	
	@GetMapping("/list")
	public String list(Model model) {
		List<Person> list = svc.list();
		model.addAttribute("list", list);
		return "thymeleaf/person/personList";
	}
	
	@GetMapping("/detail/{num}")
	public String detail(@PathVariable("num") int num, Model model) {
		
		model.addAttribute("p", svc.detail(num));
		return "thymeleaf/person/personDetail";
	}
	@GetMapping("/edit/{num}")
	public String edit(@PathVariable("num") int num,Model model) {
		
		model.addAttribute("p", svc.detail(num));
		return "thymeleaf/person/edit";
	}
	
	@PostMapping("/update")
	@ResponseBody
	public Map<String,Object> update(Model model,Person person) {
		System.out.println("111");
		return svc.update(person);
	}
	
	@GetMapping("/delete/{num}")
	@ResponseBody
	public Map<String,Object> delete(@PathVariable("num") int num) {
		
		return svc.delete(num);
	}
	

}
