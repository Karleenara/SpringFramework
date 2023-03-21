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
		//dto - seq : �θ���� seq �̴�. 
		//�θ���� ������ �����;� �Ѵ� 
		BoardDto parentDto = BoardDao.getView(dto);
		
		dto.setGroup_id(parentDto.getGroup_id());
		dto.setDepth(parentDto.getDepth()+1);
		dto.setG_level(parentDto.getG_level()+1);
		
		//�θ�� ������� update level 
		BoardDao.update_level(parentDto); //�θ��
		BoardDao.reply(dto);//����� �߰��ȴ�. 	
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
