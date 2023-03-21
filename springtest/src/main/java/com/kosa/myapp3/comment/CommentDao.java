package com.kosa.myapp3.comment;

import java.util.List;

public interface CommentDao {
	List<CommentDto> getList(CommentDto dto);
	
	void insert(CommentDto dto);
}
