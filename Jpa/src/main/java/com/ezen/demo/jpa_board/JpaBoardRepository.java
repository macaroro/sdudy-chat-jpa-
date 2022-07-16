package com.ezen.demo.jpa_board;


import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;



//dao의 역할
public interface JpaBoardRepository extends JpaRepository<JpaBoard, Integer> {
	//테이블과 프라이머리 키도 암
	
	//jpa는 메소드 이름을 이용하여 sql을 생성
	
	
	List<JpaBoard> findBytitle(String title);
	
	@Transactional
	@Modifying
	@Query("UPDATE JpaBoard b SET b.title=?2, b.contents=?3 WHERE b.num=?1")
	int update(int num,String title, String contents);

	Page<JpaBoard> findAll(Pageable pageable);
}