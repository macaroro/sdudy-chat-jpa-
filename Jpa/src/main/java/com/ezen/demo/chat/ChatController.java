package com.ezen.demo.chat;

import java.util.*;


import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/ws")
public class ChatController
{
   @GetMapping("")
   @ResponseBody
   public String index()
   {
      return "WebSocket Test";
   }
   
   @GetMapping("/chat")
    public String chat(Locale locale,Model model) {
    
	   return "chat/chat";
    }
   @GetMapping("/login")
   public String login() {
	   return "chat/login";
   }
   
   @PostMapping("/login")
   @ResponseBody
    public Map<String,Object> log(HttpSession session, @RequestParam("uid") String uid) {
	  if(uid!=null) {
	   session.setAttribute("uid",uid);
	   //받은 파라미터를 http세션으로 넘김
	   Map<String,Object> map = new HashMap<>();
	   map.put("login", true);
	   map.put("uid", uid);
	   //map을 이용해 아이디와 
	   //ture값을 ajax로 넘긴다.
	   
        return map;
	  }
	  return null;
    }
   
   
}