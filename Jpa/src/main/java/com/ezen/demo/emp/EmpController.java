package com.ezen.demo.emp;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/emp")
public class EmpController {
	
	
	
	@Autowired
	private EmpRepositpry re;
	
	@GetMapping("/list")
	public  String list (Model model) {
		List<Emp> list = re.findAll();
		model.addAttribute("list", list);
		
	
		return "emp/empList";

}
	   @GetMapping("/test1")
	   @ResponseBody
	   public String test1()
	   {
	      String ename = re.ename_by_empno(7369);
	     
	      return ename;
	   }
	   
	   @GetMapping("/test2")
	   @Transactional
	   @ResponseBody
	   public Emp test2()
	   {

		   System.out.println(re.cur_by_empno(7369));
	      return re.cur_by_empno(7369);
	   }

}
