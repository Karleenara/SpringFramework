package com.kosa.myapp3.board;

import java.util.List;

public interface BoardService {
	int getTotalCnt(BoardDto dto);

	List<BoardDto> getList(BoardDto dto);

	void insert(BoardDto dtol);

	BoardDto getView(BoardDto dto);

	void reply(BoardDto dto);

	void update_level(BoardDto dto);

	void modify(BoardDto dto);
	
	void delete(BoardDto dto);

}
