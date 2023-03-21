package com.kosa.myapp3.comment;

import java.util.List;

public interface CommentService {
	List<CommentDto> getList(CommentDto dto);
	
	void insert(CommentDto dto);
}
