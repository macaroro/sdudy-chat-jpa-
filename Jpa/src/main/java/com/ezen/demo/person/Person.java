package com.ezen.demo.person;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Entity
@Table(name="person")//데이터가 들어갈 테이블 지정
//테이브을 만들수 있는 정보를 줘야 한다
@Component
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, 
	generator ="PERSON_NUM_SEQ")//시퀸스를 쓰겠다, generator에서는 시퀸스 이름 설정
	@SequenceGenerator(sequenceName ="PERSON_NUM_SEQ", allocationSize = 1,
	name="PERSON_NUM_SEQ")
	private int num;//pk의 역할을 지정
	
	@NotEmpty(message="필수 입력입니다")
	@Size(min=2, message="2자 이상 입력하세요")
	//오류메세지를 직접 정의하지 않더라고 메세제 사용가능
	//message properties에 설정가능
	private String name;
	@NotEmpty
	@Size(min = 5)
	private String email;
	
	private int age;
	
	
	
	


	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	

}
