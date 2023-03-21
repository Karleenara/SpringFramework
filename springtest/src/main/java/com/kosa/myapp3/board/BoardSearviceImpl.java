package com.kosa.myapp3.board;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service("boardService")
public class BoardSearviceImpl implements BoardService{
	@Resource(name="boardDao")
	BoardDao BoardDao;
	
	@Override
	public int getTotalCnt(BoardDto dto) {
		return BoardDao.getTotalCnt(dto);
	}

	@Override
	public List<BoardDto> getList(BoardDto dto) {
		return BoardDao.getList(dto);
	}

	@Override
	public void insert(BoardDto dto) {
		BoardDao.insert(dto);
	}

	@Override
	public BoardDto getView(BoardDto dto) {
		return BoardDao.getView(dto);
	}

	@Override
	public void update_level(BoardDto dto) {
		BoardDao.update_level(dto);
	}

	@Override
	public void reply(BoardDto dto) {
		//dto - seq : 부모글의 seq 이다. 
		//부모글의 정보를 가져와야 한다 
		BoardDto parentDto = BoardDao.getView(dto);
		
		dto.setGroup_id(parentDto.getGroup_id());
		dto.setDepth(parentDto.getDepth()+1);
		dto.setG_level(parentDto.getG_level()+1);
		
		//부모글 기반으로 update level 
		BoardDao.update_level(parentDto); //부모글
		BoardDao.reply(dto);//답글이 추가된다. 	
	}

	@Override
	public void modify(BoardDto dto) {
		BoardDao.modify(dto);
	}

	@Override
	public void delete(BoardDto dto) {
		BoardDao.delete(dto);
	}
	
}
