package com.ezen.demo.message;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
	@Autowired
	private MessageResipatory dao;

	public List<Message> list(String receiver) {
		
		return dao.findByReceiver(receiver);
	}
	
	public Long countMessage(String id) {
		return dao.countByReceiver(id);
	}
	
	public boolean saveAll(List<Message> list) {
	dao.saveAll(list);
	return true;
	}

}
