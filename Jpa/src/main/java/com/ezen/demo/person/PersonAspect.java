package com.ezen.demo.person;


import java.util.*;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Configuration
public class PersonAspect {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	
//	@Before("execution(* com.ezen.demo.person.PersonService.*(..))")
//	public void beforeInput(JoinPoint join) throws Exception  {
//		
//		HttpSession session = ((ServletRequestAttributes)(RequestContextHolder.currentRequestAttributes())).getRequest().getSession();
//		System.out.println(session.getAttribute("id"));
//		Object obj = session.getAttribute("id");
//		if(obj==null) {
//			log.info("로그인이 요구됨");
//			throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
//		}else {
//			log.info("{} 로그인 성공", obj.toString());
//		}
//		
//		
//	}
	
	
	
	

}
