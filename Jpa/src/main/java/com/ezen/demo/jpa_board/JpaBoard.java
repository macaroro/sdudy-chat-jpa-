package com.ezen.demo.jpa_board;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;



@Entity
@Table(name="jpaboard")//데이터가 들어갈 테이블 지정
//테이브을 만들수 있는 정보를 줘야 한다
@Component
public class JpaBoard {
	
		
		@Id
		@GeneratedValue(strategy = GenerationType.SEQUENCE, 
		generator ="JPA_NUM_SEQ")//시퀸스를 쓰겠다, generator에서는 시퀸스 이름 설정
		@SequenceGenerator(sequenceName ="JPA_NUM_SEQ", allocationSize = 1,
		name="JPA_NUM_SEQ")
		private int num;//pk의 역할을 지정
		
		private String author;
		private String title;
		private String contents;
		private Date wdate;
		
		public int getNum() {
			return num;
		}
		public void setNum(int num) {
			this.num = num;
		}
		public String getAuthor() {
			return author;
		}
		public void setAuthor(String author) {
			this.author = author;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getContents() {
			return contents;
		}
		public void setContents(String contents) {
			this.contents = contents;
		}
		public Date getWdate() {
			return wdate;
		}
		public void setWdate(Date wdate) {
			this.wdate = wdate;
		}
		
		

}
