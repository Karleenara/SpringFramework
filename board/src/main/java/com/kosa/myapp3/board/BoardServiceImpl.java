package com.kosa.myapp3.board;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service("boardService")
public class BoardServiceImpl implements BoardService{

	@Resource(name="boardDao")
	BoardDao boardDao;
	
	@Override
	public int getTotalCnt(BoardDto dto) {
		
		return boardDao.getTotalCnt(dto);
	}

	@Override
	public List<BoardDto> getList(BoardDto dto) {
		return boardDao.getList(dto);
	}

	@Override
	public void insert(BoardDto dto) {
		boardDao.insert(dto);
		
	}

	@Override
	public BoardDto getView(BoardDto dto) {
		
		return boardDao.getView(dto);
	}

	@Override
	public void reply(BoardDto dto) {
		//dto - seq : 부모글의 seq 이다. 
		//부모글의 정보를 가져와야 한다 
		BoardDto parentDto = boardDao.getView(dto);
		
		dto.setGroup_id(parentDto.getGroup_id());
		dto.setDepth(parentDto.getDepth()+1);
		dto.setG_level(parentDto.getG_level()+1);
		
		//부모글 기반으로 update level 
		boardDao.update_level(parentDto); //부모글
		boardDao.reply(dto);//답글이 추가된다. 		
	}

	@Override
	public void update(BoardDto dto) {
		boardDao.update(dto);
	}

	@Override
	public void delete(BoardDto dto) {
		boardDao.delete(dto);
	}

}






