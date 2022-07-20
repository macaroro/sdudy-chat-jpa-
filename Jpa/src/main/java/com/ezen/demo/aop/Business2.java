package com.ezen.demo.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Business2 
{
    @Autowired
    private Dao2 dao2;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public String calculateSomething() {
    	 String value = dao2.retrieveSomething();
         logger.info("In Business - {}", value);
        return value;
    }
}