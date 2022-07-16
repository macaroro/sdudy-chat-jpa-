package com.ezen.demo.jpa_board;

import java.awt.print.Pageable;
import java.sql.Date;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@Service
public class JpaBoardService {
	
	@Autowired
	private JpaBoardRepository board;
	
	public Map<String,Object> save(JpaBoard jpa) {
		java.sql.Date now = new java.sql.Date(new java.util.Date().getTime());
		 
	
		jpa.setWdate(now);

		
		JpaBoard savedUser = board.save(jpa);
		ResponseEntity<JpaBoard> en = new ResponseEntity<>(savedUser,HttpStatus.OK);
		Map<String,Object> map = new HashMap<>();
		 map.put("respository", en);
		 map.put("saved", true);
		return map;
	}
	
	
	public Page<JpaBoard>  listBoard(Pageable pageable) {
	
		//메모리에 있는 entiti객체를 db에 저장
		
		
		//List<JpaBoard > list = board.findAllById(num);
		Page<JpaBoard> list =(Page<JpaBoard>) board.findAll();

		 
	return  list;
	}
	
	public JpaBoard  getUser(@PathVariable("num") int num) {

		//메모리에 있는 entiti객체를 db에 저장
		
		
	Optional<JpaBoard> op = board.findById(num);
		
	JpaBoard bbs = op.get();
	
 return bbs;
	}
	
	
	
	public Map<String, Object>  update(JpaBoard jpa) {
		
		int num = jpa.getNum();
		String title = jpa.getTitle();
		String contents = jpa.getContents();
		 
		int n = board.update(num, title, contents);
		
		Map<String,Object> map = new HashMap<>();
		 
		 map.put("updated",n>0);
		return  map;
	
}
	
	public Map<String, Object>  delete(@PathVariable("num") int num) {

		//메모리에 있는 entiti객체를 db에 저장
		
		
	    board.deleteById(num);
	    
	    Map<String,Object> map = new HashMap<>();
	    map.put("deleted",true);
		 
		 return map;
				 
		

	}


	public int[] getLinkRange(Page<JpaBoard> pageInfo) {
		int start = 0;
		int end = 0;

		if (pageInfo.getNumber() - 2 < 0) {
			start = 0;
		} else {
			start = pageInfo.getNumber() - 2;
		}

		if (pageInfo.getTotalPages() < (start + 4)) {
			end = pageInfo.getTotalPages();
			start = (end - 4) < 0 ? 0 : (end - 4);
		} else {
			end = start + 4;
		}
		return new int[] { start, end };
	}
	
}
