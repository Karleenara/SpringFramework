package com.kosa.myapp3.member;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {
	@Resource(name="memberService")
	MemberService service;
	
	@ResponseBody
	@RequestMapping("/member/save")
	Map<String, Object>member_save(MemberDto dto){
		service.Member_insert(dto);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result","success");
		
		return map;
	}
	
	@RequestMapping("/member/write")
	String member_write(MemberDto dto) {
		return "member/member";
	}
}
