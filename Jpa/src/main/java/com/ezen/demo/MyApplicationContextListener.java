package com.ezen.demo;

import java.util.*;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.ezen.demo.message.Message;
import com.ezen.demo.message.MessageService;




@Component
public class MyApplicationContextListener implements ApplicationListener
{
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	
   @Override
   public void onApplicationEvent(ApplicationEvent event) 
   {
      log.info("애플리케이션 이벤트 발생={}", event);
      if(event instanceof ContextClosedEvent)
      {
    	  log.info("애플리케이션 종료 이벤트");
    	  ContextClosedEvent e = (ContextClosedEvent) event;
          
          ApplicationContext appContext = e.getApplicationContext();
          if (!(appContext instanceof WebApplicationContext)) return;
          
          WebApplicationContext ctx = (WebApplicationContext) e.getApplicationContext();
          ServletContext context = ctx.getServletContext();
    	  Object obj = context.getAttribute("list");
    	  System.out.println(obj);
          if(obj==null) return;
          
          List<Message> list = (List<Message>) obj;
          
          // 리스너에서는 DI 이 작동하지 않으므로 콘트롤러에서 서비스 객체를 세션에 저장했다가 아래처럼 사용하면 됨
          MessageService svc = (MessageService)context.getAttribute("svc");
          svc.saveAll(list);  // 다수개의 엔티티를 동시에 저장 가능
        
         
      }
      else if(event instanceof ContextRefreshedEvent)
      {
         log.info("애플리케이션 리프레시 이벤트");
         ContextRefreshedEvent e = (ContextRefreshedEvent) event;
         
          ApplicationContext appContext = e.getApplicationContext();
          if (!(appContext instanceof WebApplicationContext)) return;
          
          WebApplicationContext ctx = (WebApplicationContext) e.getApplicationContext();
          ServletContext context = ctx.getServletContext();
        
      }
   }
}