package com.ezen.demo.emp;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import org.springframework.stereotype.Component;


@Entity
@Component
@Table(name="emp")//데이터가 들어갈 테이블 지정
//테이브을 만들수 있는 정보를 줘야 한다
@NamedStoredProcedureQueries({
	   @NamedStoredProcedureQuery(name="sp_ename_by_empno", 
	      procedureName = "ename_by_empno", 
	      parameters = {
	            @StoredProcedureParameter(mode=ParameterMode.IN, name="p_empno", type=Integer.class),
	            @StoredProcedureParameter(mode=ParameterMode.OUT, name="p_ename", type=String.class)
	      }
	   ),
	   @NamedStoredProcedureQuery(name = "sp_cur_by_empno", 
	      procedureName = "cur_by_empno",
	      resultClasses = Emp.class,
	      parameters = {
	            @StoredProcedureParameter(mode=ParameterMode.INOUT, type=Integer.class),
	            @StoredProcedureParameter(mode=ParameterMode.REF_CURSOR, type=void.class)
	            //커서를 리턴할때는 위치 기반의 파라미터 선언
	            //만들어진 프로시저의 파라미터 순서
	      }
	   ),
	   @NamedStoredProcedureQuery(name = "sp_emp_by_deptno", 
	      procedureName = "emp_by_deptno",
	      resultClasses = Emp.class,
	      parameters = {
	            @StoredProcedureParameter(mode=ParameterMode.IN, type=Integer.class),
	            @StoredProcedureParameter(mode=ParameterMode.REF_CURSOR, type=void.class)
	            //커서를 리턴할때는 위치 기반의 파라미터 선언
	            //만들어진 프로시저의 파라미터 순서
	      }
	   )
	})

public class Emp {
	
	@Id
	private int empno;
	private String ename;

	private java.sql.Date hiredate;
	private int sal;
	
	private int deptno;
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}

	public java.sql.Date getHiredate() {
		return hiredate;
	}
	public void setHiredate(java.sql.Date hiredate) {
		this.hiredate = hiredate;
	}
	public int getSal() {
		return sal;
	}
	public void setSal(int sal) {
		this.sal = sal;
	}

	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	
	
	
	
}
