package com.ezen.demo.message;

import java.util.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequestMapping("/ms")
public class MessageController {
	
	@Autowired
	private ServletContext con;
	
	@Autowired
	private MessageService svc;
	
	@GetMapping("/login")
	public String login() {
		return "thymeleaf/msg/login";
		
	}
	
	@GetMapping("/index")
	public String index(Model model,HttpSession ss) {
		ss.removeAttribute("list2");
		
		model.addAttribute("sender", con.getAttribute("sender"));
		return "thymeleaf/msg/index";
		
	}
	
	@GetMapping("/access")
	public String access( @RequestParam("sender") String id, Model model) {
		con.setAttribute("sender", id);//app영역에 저장
		con.setAttribute("svc", svc);
		svc.countMessage(id);//저장된 id와 같은 수신자가 있는지 확인하고 개수 가져오기
		String reciver = con.getAttribute("sender").toString();
		model.addAttribute("count", reciver+"님께 온 메세지 개수는 "+svc.countMessage(id)+"개 있습니다");
		model.addAttribute("sender", con.getAttribute("sender"));
	

		return "thymeleaf/msg/index";
		
	}
	
	@GetMapping("/wirte")
	public String wirte(Model model) {
		model.addAttribute("ms", con.getAttribute("sender"));
		return "thymeleaf/msg/write";
		
	}
	List<Message> list = new ArrayList<>();
	
	@PostMapping("/save")
	public String save(Message msg, Model model, HttpSession ss) {
	
		java.sql.Timestamp now= new java.sql.Timestamp(new java.util.Date().getTime());
		msg.setWdate(now);
         list.add(msg);
         con.setAttribute("list", list);
         ss.setAttribute("list2", list);
         model.addAttribute("sender", con.getAttribute("sender"));
         return "redirect:/ms/wirte";
	
		
	}
	
	@GetMapping("/read/{sender}")
	public String read(@PathVariable("sender") String id, Model model, HttpSession ss) {
		model.addAttribute("list", ss.getAttribute("list2"));
		if(ss.getAttribute("list2")==null) {
			model.addAttribute("list", svc.list(id));
		}
		
		return "thymeleaf/msg/msgList";
		
	}
	

}
