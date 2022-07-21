package com.ezen.demo.person;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserResipatory  extends JpaRepository<User, Integer>{

	//void save(Map<String, List<User>> map);

}
