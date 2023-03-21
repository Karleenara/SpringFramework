package com.kosa.myapp1.board;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardController {
	@Resource(name = "boardService")
	BoardService service;

	@RequestMapping(value = "/board/list")
	public String getList(BoardDto dto, Model model) {
		model.addAttribute("list", service.getList(dto));

		return "board/list";
	}

	@RequestMapping(value = "/board/write")
	public String write() {
		return "/board/write";
	}

	@RequestMapping(value = "/board/save")
	public String save(BoardDto dto) {
		service.insert(dto);
		return "redirect:/board/list"; // write.jsp로 이동만
	}

	@RequestMapping(value = "/board/view")
	public String view(String seq, Model model) {
		model.addAttribute("dto", service.getView(seq));
		return "board/view";
	}

}
