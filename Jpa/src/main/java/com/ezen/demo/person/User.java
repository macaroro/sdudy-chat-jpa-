package com.ezen.demo.person;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="user_track")//데이터가 들어갈 테이블 지정
//테이브을 만들수 있는 정보를 줘야 한다
@Component
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, 
	generator ="USER_TRACK_NUM_SEQ")//시퀸스를 쓰겠다, generator에서는 시퀸스 이름 설정
	@SequenceGenerator(sequenceName ="USER_TRACK_NUM_SEQ", allocationSize = 1,
	name="USER_TRACK_NUM_SEQ")
	private int num;
	private String id;
	private java.sql.Timestamp wdate;
	private String menu;
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Timestamp getWdate() {
		return wdate;
	}
	public void setWdate(Timestamp now) {
		this.wdate = now;
	}
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
	
	

}
