package com.kosa.myapp3.comment;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("commentDao")
public class CommentDaoImpl implements CommentDao {

	@Autowired
	SqlSessionTemplate sm;
	
	@Override
	public List<CommentDto> getList(CommentDto dto) {
		return sm.selectList("Comment_getList", dto);
	}

	@Override
	public void insert(CommentDto dto) {
		sm.insert("Comment_insert", dto);
	}
	
}
