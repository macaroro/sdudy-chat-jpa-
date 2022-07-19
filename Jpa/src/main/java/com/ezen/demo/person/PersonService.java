package com.ezen.demo.person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
	@Autowired
	PersonResipatory dao;

	public Map<String, Object> save(Person person) {
		
		Map<String,Object> map = new HashMap<>();
		map.put("p", dao.save(person));
		map.put("saved", true);
		return map;
	}

	public List<Person> list() {
		
		return dao.findAll();
	}

	public Person detail(int num) {
		 Optional<Person> op = dao.findById(num);
		 Person pr = op.get();
		
		return pr;
	}





	public Map<String, Object> delete(int num) {
		Map<String,Object> map = new HashMap<>();
		 dao.deleteById(num);
		map.put("deleted", true);
		return map;
		
		
	}

	public Map<String, Object> update(Person person) {
		Map<String,Object> map = new HashMap<>();
		int num = person.getNum();
		String email = person.getEmail();
		map.put("p", dao.update(num, email));
		map.put("updated", dao.update(num, email)>0);
		
		return map;
	}

}
