package com.kosa.myapp3.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.kosa.myapp3.comment.CommentDto;
import com.kosa.myapp3.comment.CommentService;
import com.kosa.myapp3.common.CommonConst;
import com.kosa.myapp3.common.FileUploadUtil;

@Controller
public class BoardController {
	@Resource(name = "boardService")
	BoardService service;

	@Resource(name = "commentService")
	CommentService commentService;

	@RequestMapping(value = "/board/list")
	public String getList(BoardDto dto, Model model) {
		model.addAttribute("totalCnt", service.getTotalCnt(dto));
		model.addAttribute("boardList", service.getList(dto));

		return "board/board_list";
	}

	@RequestMapping(value = "/board/write")
	public String board_write(BoardDto dto, Model model) {
		BoardDto tempDto = new BoardDto();
		model.addAttribute("dto", tempDto);
		return "board/board_write";
	}

	// view(seq) -> reply(seq)
	@RequestMapping(value = "/board/reply")
	public String reply(BoardDto dto, Model model) {
		// 부모글 정보 가져오기
		BoardDto tempDto = service.getView(dto);
		model.addAttribute("dto", tempDto);
		return "board/board_write";
	}

	// ajax로 대답해야 한다
	@ResponseBody
	@RequestMapping(value = "/board/reply_save")
	public Map<String, String> reply_save(BoardDto dto, Model model, MultipartHttpServletRequest multi) {
		Map<String, String> map = new HashMap<String, String>();
		// 트랜잭션 처리를 해놓으면 콘트롤러에서 예외처리를 해줘야 한다.
		// 서비스에서 예외처리하면 rollback 이 적용이 안된다.
		try {
			List<MultipartFile> fileList = new ArrayList<MultipartFile>();
			fileList.add(multi.getFile("file1"));
			fileList.add(multi.getFile("file2"));
			fileList.add(multi.getFile("file3"));

			List<String> fileNameList = new ArrayList<String>();

			// 파일업로드 경로 지정하기
			String path = CommonConst.UPLOADPATH + "\\board";
			FileUploadUtil.setFilePath(path);

			FileUploadUtil.upload(fileList, fileNameList);

			dto.setFilename1(fileNameList.get(0));
			dto.setFilename2(fileNameList.get(1));
			dto.setFilename3(fileNameList.get(2));

			service.reply(dto);
			map.put("result", "success");
		} catch (Exception e) {
			map.put("result", "fail");
		}

		return map; // 리스트로 옮긴다
	}

	@ResponseBody
	@RequestMapping(value = "/board/save")
	public Map<String, String> save(BoardDto dto, MultipartHttpServletRequest multi) {
		Map<String, String> resultMap = new HashMap<String, String>();

		System.out.println(dto.getTitle());
		System.out.println(dto.getContents());
		System.out.println(dto.getFilename1());
		System.out.println(dto.getFilename2());
		System.out.println(dto.getFilename3());
		System.out.println(dto.getDepth());

		// dto 에 다른 파라미터 값들은 이미 들어와있음
		// 파일처리만 하기
		List<MultipartFile> fileList = new ArrayList<MultipartFile>();
		fileList.add(multi.getFile("file1"));
		fileList.add(multi.getFile("file2"));
		fileList.add(multi.getFile("file3"));

		List<String> fileNameList = new ArrayList<String>();

		// 경로 코드에 박으면 안됨
//		String path="C:\\web_programming\\spring_workspace1\\myapp3\\src\\main\\webapp\\upload";

		// common 파일에 CommonConst 공통상수클래스 만들어서 path 설정
		// 파일 업로드 경로 지정하기
		String path = CommonConst.UPLOADPATH + "\\board";
		FileUploadUtil.setFilePath(path);
		FileUploadUtil.upload(fileList, fileNameList);

		dto.setFilename1(fileNameList.get(0));
		dto.setFilename2(fileNameList.get(1));
		dto.setFilename3(fileNameList.get(2));

		System.out.println(dto.getFilename1());
		System.out.println(dto.getFilename2());
		System.out.println(dto.getFilename3());

		service.insert(dto);
		resultMap.put("result", "success");
		resultMap.put("message", "글이 등록되었습니다");
		return resultMap;
	}

	@RequestMapping(value = "/board/view")
	public String getView(BoardDto dto, Model model, CommentDto commentdto) {
		BoardDto resultDto = service.getView(dto);
		
		commentdto.setBoard_seq(dto.getSeq());
		model.addAttribute("commentList", commentService.getList(commentdto));
		model.addAttribute("dto", resultDto);
		return "board/board_view";
	}

	@RequestMapping(value = "/board/modify")
	public String modify(BoardDto dto, Model model) {
		// 수정할 글 정보 가져오기
		BoardDto tempDto = service.getView(dto);
		model.addAttribute("dto", tempDto);
		return "board/board_write";
	}

	@ResponseBody
	@RequestMapping(value = "/board/modify_save")
	public Map<String, String> modify_save(BoardDto dto, MultipartHttpServletRequest multi, Model model, String[] del,
			String[] old_name) {
		Map<String, String> map = new HashMap<String, String>();
		// 파일 첨부 하지 않더라도 본래의 파일명은 갖고 있어야 함

		dto.setFilename1(old_name[0]);
		dto.setFilename1(old_name[1]);
		dto.setFilename1(old_name[2]);

		String path = CommonConst.UPLOADPATH + "\\board";
		FileUploadUtil.setFilePath(path);

		List<MultipartFile> fileList = new ArrayList<MultipartFile>();
		fileList.add(multi.getFile("file1"));
		fileList.add(multi.getFile("file2"));
		fileList.add(multi.getFile("file3"));

		List<String> fileNameList = new ArrayList<String>();

		FileUploadUtil.upload(fileList, fileNameList);
		for (int i = 0; i < del.length; i++) {
			if (del[i].equals(fileNameList.get(i)))
				dto.setFilename1(fileNameList.get(i));
		}

		return map; // 리스트로 옮긴다
	}

	@ResponseBody
	@RequestMapping(value = "/board/delete")
	public Map<String, String> delete(BoardDto dto) {

		Map<String, String> resultMap = new HashMap<String, String>();
		service.delete(dto);
		resultMap.put("result", "success");
		return resultMap;
	}

	@RequestMapping(value = "/comment/list")
	public String getList(CommentDto dto, Model model) {
		model.addAttribute("commentList", commentService.getList(dto));
		return "board/board_view";
	}

	@RequestMapping(value = "/comment/write")
	public String comment_write(CommentDto dto, Model model) {
		model.addAttribute("dto", dto);
		return "board/board_view";
	}

	@ResponseBody
	@RequestMapping(value = "/comment/comment_save")
	public Map<String, String> comment_save(CommentDto dto, Model model) {
		Map<String, String> map = new HashMap<String, String>();

		commentService.insert(dto);
		map.put("result", "success");
		map.put("message", "댓글 등록 완!");

		return map; // 리스트로 옮긴다
}

}
