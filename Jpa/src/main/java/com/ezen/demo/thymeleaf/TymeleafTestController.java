package com.ezen.demo.thymeleaf;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezen.demo.emp.Emp;

@Controller
@RequestMapping("/th")
public class TymeleafTestController {
	
	@GetMapping("/index")
	public String index() {
		
		return"thymeleaf/index";
	}
	
	
	@GetMapping("/test1")//문자열을 출력
	public String test1(Model model) {
		
		 model.addAttribute("item", "min");
		return"thymeleaf/index";
		//model에 담겨 있는 string 넘기기
	}
	
	@GetMapping("/test2")//객체를 출력
	public String test2(Model model) {
		Emp emp = new Emp();
		emp.setEmpno(0);
		emp.setEname("alice");
		emp.setDeptno(50);
		emp.setHiredate(java.sql.Date.valueOf("2010-10-21"));
		emp.setSal(1300);
		 model.addAttribute("item", "min");
		 model.addAttribute("emp", emp);
		 //객체를 html에 전달
		return"thymeleaf/index";
	}
	
	@GetMapping("/test3")//리스트 출력
	public String test3(Model model) {
		
		List<Emp> list = new ArrayList<>();
		Emp emp = new Emp();
		emp.setEmpno(0);
		emp.setEname("alice");
		emp.setDeptno(50);
		emp.setHiredate(java.sql.Date.valueOf("2010-10-21"));
		emp.setSal(1300);
		
		Emp emp2 = new Emp();
		emp2.setEmpno(1);
		emp2.setEname("roro");
		emp2.setDeptno(30);
		emp2.setHiredate(java.sql.Date.valueOf("1998-10-16"));
		emp2.setSal(1500);
		list.add(emp2);
		list.add(emp);
		 model.addAttribute("list", list);
		 model.addAttribute("emp", emp2);
		 model.addAttribute("role", "ADMIN");
		 //객체를 html에 전달
		return"thymeleaf/index";
	}
	
	@GetMapping("/menu")//리스트 출력
	public String list(Model model) {
		
		List<Emp> list = new ArrayList<>();
		Emp emp = new Emp();
		emp.setEmpno(0);
		emp.setEname("alice");
		emp.setDeptno(50);
		emp.setHiredate(java.sql.Date.valueOf("2010-10-21"));
		emp.setSal(1300);
		
		Emp emp2 = new Emp();
		emp2.setEmpno(1);
		emp2.setEname("roro");
		emp2.setDeptno(30);
		emp2.setHiredate(java.sql.Date.valueOf("1998-10-16"));
		emp2.setSal(1500);
		list.add(emp2);
		list.add(emp);
		 model.addAttribute("list", list);
	
		 //객체를 html에 전달
		return"thymeleaf/list";
	}
	
	@GetMapping("/input")
	public String input() {
		return"thymeleaf/inputForm";
	}
	
	@PostMapping("/save")
	@ResponseBody
	public Emp save(Emp emp) {
		
		return emp;
	}

}
