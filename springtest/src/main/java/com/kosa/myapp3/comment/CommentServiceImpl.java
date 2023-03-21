package com.kosa.myapp3.comment;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service("commentService")
public class CommentServiceImpl implements CommentService {
	@Resource(name="commentDao")
	CommentDao commentDao;
	
	@Override
	public List<CommentDto> getList(CommentDto dto) {
		return commentDao.getList(dto);
	}

	@Override
	public void insert(CommentDto dto) {
		commentDao.insert(dto);
	}
	
}
