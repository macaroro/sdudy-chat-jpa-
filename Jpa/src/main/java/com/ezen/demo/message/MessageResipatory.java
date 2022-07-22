package com.ezen.demo.message;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageResipatory extends JpaRepository<Message, Integer>{
	
	List<Message> findByReceiver(String receiver);
	
	Long countByReceiver(String receiver);
	

}
