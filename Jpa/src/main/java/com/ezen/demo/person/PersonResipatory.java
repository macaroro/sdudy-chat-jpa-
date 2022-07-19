package com.ezen.demo.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PersonResipatory  extends JpaRepository<Person, Integer>{
	
	@Transactional
	@Modifying
	@Query("UPDATE Person p SET p.email=?2 WHERE p.num=?1")
	int update(int nun, String email);

}
