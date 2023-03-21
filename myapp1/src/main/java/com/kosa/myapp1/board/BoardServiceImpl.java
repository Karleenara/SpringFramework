package com.kosa.myapp1.board;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("boardService")
public class BoardServiceImpl implements BoardService{
	
//	@Autowired
//	BoardDao boardDao; 
	
	@Resource(name="boardDao")
	BoardDao boardDao2;

	@Override
	public List<BoardDto> getList(BoardDto dto) {
		// TODO Auto-generated method stub
		return boardDao2.getList(dto);
	}

	@Override
	public BoardDto getView(String seq) {
		return boardDao2.getView(seq);
	}

	@Override
	public void insert(BoardDto dto) {
		// dao - table 단위
		// service - 화면 단위, 여러개 dao 소유 가능
		boardDao2.insert(dto);
	}

	@Override
	public void update(BoardDto dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(BoardDto dto) {
		// TODO Auto-generated method stub
		
	}



	
}
