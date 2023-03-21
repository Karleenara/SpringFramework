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

import com.kosa.myapp3.common.CommonConst;
import com.kosa.myapp3.common.FileUploadUtil;

//4번)
@Controller
public class BoardController {
	
	//5번)
	@Resource(name = "BoardService")
	BoardService service;
	
	@RequestMapping(value="/board/list")
	public String getList(BoardDto dto, Model model)
	{
		model.addAttribute("totalCnt", service.getTotalCnt(dto));
		model.addAttribute("boardList", service.getList(dto));
		
		return "board/board_list";
	}
	
	@RequestMapping(value="/board/write")
	public String write(BoardDto dto, Model model)
	{
		BoardDto tempDto  = new BoardDto();
		model.addAttribute("boardDto", tempDto);
		return "board/board_write";
	}
	
	//view(seq) -> reply(seq) 
	@RequestMapping(value="/board/reply")
	public String reply(BoardDto dto, Model model)
	{
		//부모글 정보 가져오기 
		BoardDto tempDto  = service.getView(dto);
		model.addAttribute("boardDto", tempDto);
		return "board/board_write";
	}
	
	//ajax로 대답해야 한다 
	@ResponseBody
	@RequestMapping(value="/board/reply_save")
	public Map<String, String> reply_save(BoardDto dto, Model model, 
			MultipartHttpServletRequest multi)
	{
		Map<String, String> map = new HashMap<String, String>();
		//트랜잭션 처리를 해놓으면  콘트롤러에서 예외처리를 해줘야 한다. 
		//서비스에서 예외처리하면 rollback 이 적용이 안된다. 
		try
		{
			List<MultipartFile> fileList = new ArrayList<MultipartFile>();
			fileList.add( multi.getFile("file1"));
			fileList.add( multi.getFile("file2"));
			fileList.add( multi.getFile("file3"));
			
			List<String> fileNameList = new ArrayList<String>();
			
			//파일업로드 경로 지정하기
			String path = CommonConst.UPLOADPATH+"\\board";
			FileUploadUtil.setFilePath(path);
			
			FileUploadUtil.upload(fileList, fileNameList);
			
			dto.setFilename1(fileNameList.get(0));
			dto.setFilename2(fileNameList.get(1));
			dto.setFilename3(fileNameList.get(2));
			
			service.reply(dto);
			map.put("result", "success");
		}
		catch(Exception e)
		{
			map.put("result", "fail");
		}
	
		return map; //리스트로 옮긴다 
	}
	
	
	
	
	@ResponseBody
	@RequestMapping(value="/board/save")
	public Map<String, String> save(BoardDto dto,  MultipartHttpServletRequest multi)
	{
		//dto에 다른 파라미터값들은 이미 들어와있음 
		//파일처리만 하면된다. 
		List<MultipartFile> fileList = new ArrayList<MultipartFile>();
		fileList.add( multi.getFile("file1"));
		fileList.add( multi.getFile("file2"));
		fileList.add( multi.getFile("file3"));
		
		List<String> fileNameList = new ArrayList<String>();
		
		//파일업로드 경로 지정하기
		String path = CommonConst.UPLOADPATH+"\\board";
		FileUploadUtil.setFilePath(path);
		
		FileUploadUtil.upload(fileList, fileNameList);
		
		dto.setFilename1(fileNameList.get(0));
		dto.setFilename2(fileNameList.get(1));
		dto.setFilename3(fileNameList.get(2));
		
		service.insert(dto);
		
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("result", "success");
		return resultMap;
	}
	
	@RequestMapping(value="/board/view")
	public String getView(BoardDto dto, Model model)
	{
		//request.setAttribute("boardDto", service.getView(dto));
		model.addAttribute("boardDto", service.getView(dto));
		
		return "board/board_view";
	}
	
	@RequestMapping(value="/board/modify")
	public String modify(BoardDto dto, Model model)
	{
		//수정할글 정보 가져오기 
		BoardDto tempDto  = service.getView(dto);
		model.addAttribute("boardDto", tempDto);
		return "board/board_write";
	}
	
	@ResponseBody
	@RequestMapping(value="/board/modify_save")
	public Map<String, String> modify_save(BoardDto dto, Model model,
			String []del, String []old_name, 
			MultipartHttpServletRequest multi)
	{
		Map<String, String> map= new HashMap<String, String>();
		//파일 첨부를 하지 않더라도 본래의 파일명은 갖고 있어야 한다 
		//디비에서 가져온 기본값을 넣어 놓자 
		dto.setFilename1(old_name[0]);
		dto.setFilename2(old_name[1]);
		dto.setFilename3(old_name[2]);
		
		String path = CommonConst.UPLOADPATH+"\\board";
		FileUploadUtil.setFilePath(path);
		
		List<MultipartFile> fileList = new ArrayList<MultipartFile>();
		
		fileList.add( multi.getFile("file1"));
		fileList.add( multi.getFile("file2"));
		fileList.add( multi.getFile("file3"));
		
		List<String> fileNameList = new ArrayList<String>();
		
		FileUploadUtil.upload(fileList, fileNameList);
		
		//첨부된 파일 처리하기 
		if( del[0].equals("1"))
			dto.setFilename1(fileNameList.get(0));
		if( del[1].equals("2"))
			dto.setFilename2(fileNameList.get(1));
		if( del[2].equals("3"))
			dto.setFilename3(fileNameList.get(2));
			
		service.update(dto);
		
		map.put("result", "success");
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="/board/delete")
	public Map<String, String> delete(BoardDto dto)
	{
		Map<String, String> resultMap = new HashMap<String, String>();
		service.delete(dto);
		resultMap.put("result", "success");
		return resultMap;
	}

}







