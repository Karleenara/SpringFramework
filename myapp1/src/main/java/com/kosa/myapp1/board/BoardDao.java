package com.kosa.myapp1.board;

import java.util.List;

public interface BoardDao {
	List<BoardDto> getList(BoardDto dto);
	BoardDto getView(String seq);
	void insert(BoardDto dto);
	void update(BoardDto dto);
	void delete(BoardDto dto);
}
