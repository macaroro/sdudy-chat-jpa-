package com.ezen.demo.person;

import java.util.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;

@Controller
@RequestMapping("/person")
@ServletComponentScan
public class PersonController {
	
	@Autowired
	PersonService svc;
	@Autowired
	PersonResipatory dao;
	
	
	@GetMapping("/login")
	public String login() {
	
		return "thymeleaf/person/login";
	}
	
	@PostMapping("/access")
	public String access(HttpSession session,
			@RequestParam("id") String id) {
		System.out.println("11");
		
		if(id!=null) {
	    session.setAttribute("id",id);}
		
		if(session.getAttribute("id")!=null) {
			return "redirect:/person/info";
		}
		
		System.out.println(session.getAttribute("id"));
	
		return "thymeleaf/login";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session)
	{
		session.invalidate();
		return "redirect:/person/info";
	}
	
//	@PostMapping("/login")
//	@ResponseBody
//	public String login(@RequestParam("uid") String uid,
//						HttpSession session)
//	{
//		// HttpSessionListener에서 @Autowired가 작동하지 않으므로 세션에 Service참조를
//		// 저장하여 리스너에서 참조할 수 있도록 준비한다
//		session.setAttribute("service", svc);
//		
//		if(uid!=null && !uid.equals("")) {
//			session.setAttribute("uid", uid);
//			return "로그인 성공";
//		}
//		
//		return "로그인 실패";
//	}
	
	
	@GetMapping("/info")
	public String input(Model model) {
		model.addAttribute("person",new Person());
		return "thymeleaf/person/infoForm";
	}
	
	
	@PostMapping("/save")
	public String save(@Valid Person person, BindingResult res, Model model) {
		
		if(res.hasErrors()) {
			
			FieldError err = res.getFieldError("email");
			//특정 필드의 에러 메시지를 가져옴
			String msg = err.getDefaultMessage();
			System.out.println(msg);
			
			
			List<FieldError> list =res.getFieldErrors();
			//모든 필드의 에러메세지를 가져옴
			for(int i=0; i<list.size();i++) {
				FieldError fe = list.get(i);
				String fname = fe.getField();
				String errMsg = fe.getDefaultMessage();
				System.out.println(errMsg);
				System.out.println(fname);
			}
			
			
			
//			List<ObjectError> list =res.getAllErrors();
			//모든 에러메세지를 가져옴
//			for(int i=0; i<list.size();i++) {
//				ObjectError oe = list.get(i);
//				String errMsg = oe.getDefaultMessage();
//				System.out.println(errMsg);
//			}
			
			return "thymeleaf/person/infoForm";
		};
		
		try {
			svc.save(person);
		}catch(HttpClientErrorException e) {
			model.addAttribute("msg", "로그인 후에 사용가능");
			return "thymeleaf/person/login";
		}
		
		
		
		//return null;
		return "redirect:/person/list";
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
