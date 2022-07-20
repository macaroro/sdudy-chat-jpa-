//package com.ezen.demo.aop;
//
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Configuration;
//
//@Aspect
//@Configuration
//public class UserAccessAspect {
//	private final Logger log = LoggerFactory.getLogger(this.getClass());
////	@After("execution(* com.ezen.demo.aop.*.*(..))")
////	//패키지,*(..)은 모든 메소드,
////	//aop.*.는 aop 패키지 안에 있는 클래스(즉 패키지에 있는 모든 클래스 그 안에 있는 모든 메소드가 실행되기 전에
////	//아래의 메소드가 돌아간다
////	//패키지 이름 앞에 있는 *은 리턴 타입을 설정(즉 모든 리턴 타입을 받겠다)
////	public void before(JoinPoint join) {
////		//Controller에서 메소드가 실행되기 전에 아래의 실행문이 먼저 돌아감 
////		//JoinPoint
////		log.info("Check for user access");
////		log.info("Allowed execution for {}", join);
////	}
//	
//	
//	@AfterReturning("execution(* com.ezen.demo.aop.*.*(..))")
//	//패키지,*(..)은 모든 메소드,
//	//aop.*.는 aop 패키지 안에 있는 클래스(즉 패키지에 있는 모든 클래스 그 안에 있는 모든 메소드가 실행되기 전에
//	//아래의 메소드가 돌아간다
//	//패키지 이름 앞에 있는 *은 리턴 타입을 설정(즉 모든 리턴 타입을 받겠다)
//	public void after(JoinPoint join,Object res) {
//		//Controller에서 메소드가 실행되기 전에 아래의 실행문이 먼저 돌아감 
//		//JoinPoint
//		log.info("실행완료");
//		//log.info("Allowed execution for {}", join);
//	}
//	
//	@AfterReturning("execution(* com.ezen.demo.aop.*.*(..))")
//	public void afterReturning(JoinPoint joinPoint, Object result)
//	{
//	}
//	
//	@Around("execution(* com.ezen.demo.aop.BankService.*(..))")
//	public void aroundAdvice(ProceedingJoinPoint jp) throws Throwable   
//	{  
//		log.info("The method aroundAdvice() before invokation of the method " + jp.getSignature().getName() + " method");  
//		try   
//		{  
//			jp.proceed();   // 실제 호출된 Core Concern 이 실행됨
//		}   
//		finally   
//		{  
//		  
//		}  
//		log.info("The method aroundAdvice() after invokation of the method " + jp.getSignature().getName() + " method");  
//	}
//
//
//}
