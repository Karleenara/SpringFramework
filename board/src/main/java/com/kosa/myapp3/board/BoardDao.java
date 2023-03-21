package com.kosa.myapp3.board;

import java.util.List;

public interface BoardDao {
	int getTotalCnt(BoardDto dto);
	List<BoardDto> getList(BoardDto dto);
	void insert(BoardDto dto);
	BoardDto getView(BoardDto dto);
	void reply(BoardDto dto);
	void update_level(BoardDto dto);
	void update(BoardDto dto);
	void delete(BoardDto dto);
}
