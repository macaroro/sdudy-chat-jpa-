package com.ezen.demo.mail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface MaliRepasitory extends JpaRepository<Mail, Integer> {
	
	@Query(value="SELECT empno FROM emp2 WHERE email=?1", nativeQuery=true)
	int selectEmpnoByEmail(String email);
	
	
	@Query(value="SELECT email FROM emp2 WHERE empno=?1", nativeQuery=true)
	String selectEmailByEmpno(int empno);

}
