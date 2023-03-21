package com.kosa.myapp1.board;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository("boardDao") 
public class BoardDaoImpl implements BoardDao {
	List<BoardDto> list = new ArrayList<BoardDto>(); 

	public BoardDaoImpl() {
		super();
		for (int i = 1; i <= 10; i++) {
			list.add(new BoardDto(i + "", "제목" + i, "작성자" + i, "내용" + i, "2023-01-27", i + ""));
		}

	}

	@Override
	public List<BoardDto> getList(BoardDto dto) {
		return list;
	}

	@Override
	public BoardDto getView(String seq) {
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).getSeq().equals(seq))
				return list.get(i);
		}
		return new BoardDto(); // 못찾았을 경우 비어있는 객체 전달
	}

	@Override
	public void insert(BoardDto dto) {
		list.add(dto);
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
