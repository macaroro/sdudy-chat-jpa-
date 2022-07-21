package com.ezen.demo.person;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Aspect
@Configuration
public class UserAspect {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserResipatory dao;
	

//	@Autowired 
//	private UserResipatory dao;
//	@AfterReturning("execution(* com.ezen.demo.person.PersonService.*(..))")
//	public void afterLog(JoinPoint join, User user) throws Exception{
//		HttpSession session = ((ServletRequestAttributes)(RequestContextHolder.currentRequestAttributes())).getRequest().getSession();
//		Object obj = session.getAttribute("id");
//		List<User> list =new ArrayList<>();
//		Map<String, List<User>> map = new HashMap<>();
//		java.sql.Date now = new java.sql.Date(new java.util.Date().getTime());
//		
//		if(obj!=null) {
//			
//			user.setId((String) obj);
//			user.setWdate(now);
//			user.setuser(join.toString());
//			
//			list.add(user);
//			map.put("list", list);
//		}else if(obj==null) {
//			dao.save(map);
//		}
//	}
	
	




		@AfterReturning("execution(* com.ezen.demo.person.PersonService.*(..))")
		public void afterReturning(JoinPoint join)  //Object result(결과값 받을때)
		{
			System.out.println(join);
			HttpSession session = ((ServletRequestAttributes)(RequestContextHolder.currentRequestAttributes())).getRequest().getSession();
			Object obj = session.getAttribute("id");
			List<User> list =new ArrayList<>();
			java.sql.Timestamp now= new java.sql.Timestamp(new java.util.Date().getTime());
			User user = new User();
			
			if(obj!=null) {
				System.out.println("in");
				user.setId(obj.toString());
				user.setWdate(now);
				user.setMenu(join.toString());
				
				list.add(user);
				dao.saveAll(list);
		};
		}
		

//		@AfterReturning(value="execution(* com.ezen.demo.person.PersonService.*(..))", returning="result")
//		public void afterReturning(JoinPoint joinPoint, Object result) 
//		{
//			String methodName = joinPoint.toShortString().split("\\.")[1].split("\\(")[0];
//			log.info("{} returned with value {}", methodName, result);
//			
//			HttpSession session = ((ServletRequestAttributes)(RequestContextHolder.currentRequestAttributes())).getRequest().getSession();
//			Object obj = session.getAttribute("histList");
//			if (obj==null) initSession(session);
//			
//			User user = new User();
//			user.setId((String)session.getAttribute("uid"));
//			user.setWdate(new java.sql.Timestamp(System.currentTimeMillis()));
//			user.setMenu(methodName);
//			
//			log.info("user={}", user);
//			
//			List<User> list = (List<User>)session.getAttribute("histList");
//			if(list.size()==0) {
//				list.add(user);
//				log.info("세션에 이동내역 저장 완료");
//				return;
//			}
//			
//			if(list.get(list.size()-1).getMenu().equals(user.getMenu())) {
//			}else {
//				list.add(user);
//				log.info("세션에 이동내역 저장 완료");
//			}
//		}
//		
//		private void initSession(HttpSession s)
//		{
//			List<User> list = new ArrayList<>();
//			s.setAttribute("histList", list);
//		}

}
