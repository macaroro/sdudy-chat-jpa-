package com.ezen.demo.mail;

import java.io.File;
import java.util.*;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/mail")
public class MailTestController {
	@Autowired
	private MailService svc;
	@Autowired
	private MailReader ss;
	

	@GetMapping("/test")
	@ResponseBody
	public boolean test() {
		return svc.sendSimpleText();
		//구글에서 네이버 메일로 간단한 텍스트를 보내기
		
	}
	
	@GetMapping("/test2")
	@ResponseBody
	public boolean test2() {
		return svc.sendMimeMessage();
		//구글에서 네이버 메일로 간단한 텍스트를 보내기
		
	}
	
	@GetMapping("/login")
	public String login() {
		return "thymeleaf/mail/login";
	}
	
	@PostMapping("/login")
	public String access(Mail mail, Model model) {
		System.out.println("1111");
		
	     model.addAttribute("empno",svc.find(mail.getEmail()));
		
		return "thymeleaf/mail/mailForm";
		
	}
	
	
	
	@GetMapping("/mailForm")
	public String form() {
		return "thymeleaf/mail/mailForm";
	}
	
	
	@PostMapping("/auth/abc123")
	@ResponseBody
	public boolean auth(@RequestParam("files") MultipartFile[] mfiles,HttpServletRequest request,
			             @RequestParam("title") String title, @RequestParam("contents") String contents,
			             @RequestParam("empno") int empno) {
		ServletContext context = request.getServletContext();
		String savePath = context.getRealPath("/WEB-INF/files");
		List<String> list = new ArrayList<>();
		System.out.println(savePath);
		for(int i=0; i<mfiles.length;i++) {
			String name = mfiles[i].getOriginalFilename();
			try {
				mfiles[i].transferTo(new File(savePath+"/"+name));
				list.add(name);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		String email = svc.findEamil(empno);
		
		svc.sendAttachMail(list,title, contents,email);
		
		
		return true; 
		//구글에서 네이버 메일로 간단한 텍스트를 보내기
		
	}
	
	@GetMapping("/read")
	public void read() {
		
		ss.readEmail();
	}
	
	@GetMapping("/delete/{num}")
	@ResponseBody
	public boolean delete(@PathVariable("num") int num) {
		
		
		ss.removeMail(num);
		return true;
	}
}
