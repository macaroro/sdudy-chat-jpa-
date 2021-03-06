package com.ezen.demo.jpa_board;


import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;










@RequestMapping("/jpaboard")
@Controller
public class JpaBoardController {

@Autowired
private JpaBoardService svc;





		@GetMapping("")
		public String board () {

			return "jpa/jpaBoard";
		}

		@PostMapping("/save")
		@ResponseBody
		public Map<String, Object> save (JpaBoard jpa) {

			return svc.save(jpa);
		}

		@GetMapping("/list")
		public  String list (Pageable pageable, Model model) {
			Page<JpaBoard> page = svc.getList(pageable);
		    int[] link = svc.getLinkRange(page);

			model.addAttribute("pageInfo",page);
			model.addAttribute("start",link[0]);
			model.addAttribute("end",link[1]);
			return "jpa/list";

}

	@GetMapping("/detail/{num}")
		public  String detail (Model model, @PathVariable("num") int num) {


			model.addAttribute("bbs", svc.getUser(num));

			return "jpa/detail";

}

		@GetMapping("/edit/{num}")
		public String edit (@PathVariable("num") int num,Model model) {
			model.addAttribute("bbs", svc.getUser(num));
			return "jpa/update";
		}

		@PostMapping("/update")
		@ResponseBody
	public Map<String, Object> upadte (JpaBoard jpa) {

			return svc.update(jpa);
		}



		@GetMapping("/delete/{num}")
		@ResponseBody
		public Map<String, Object> delete (@PathVariable("num") int num) {


			return svc.delete(num);
	}


}