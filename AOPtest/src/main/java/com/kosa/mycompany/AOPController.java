package com.kosa.mycompany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AOPController {
	@Autowired
	SampleService service;
	
	@RequestMapping(value = "/aop")
	public String aop_test(Model model) {
		service.displayName();
		service.displayNumber();
		model.addAttribute("result", service.displayNumber(1000));
		
		return "aop_test";
	}
}
