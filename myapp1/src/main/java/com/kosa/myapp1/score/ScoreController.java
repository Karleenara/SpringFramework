package com.kosa.myapp1.score;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ScoreController {
	@Autowired
	ScoreService service;
	
	@RequestMapping(value="/score/list")
	public String getList(ScoreDto dto, Model model) {
		model.addAttribute("list", service.getList(dto));
		
		return "score/list";
	}
}
