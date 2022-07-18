package com.ezen.demo.emp;

import javax.transaction.Transactional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

@Transactional
public interface EmpRepositpry extends JpaRepository<Emp, Integer> {
	
	// ename_by_empno
	   @Procedure(name="sp_ename_by_empno") 
	   String ename_by_empno(@Param("p_empno") int empno);
	   
	   @Procedure(name="sp_cur_by_empno") 
	   Emp cur_by_empno(@Param("p_empno") int empno);

}