package com.kosa.myapp3.board;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("boardDao")
public class BoardDaoImpl implements BoardDao {

	@Autowired
	SqlSessionTemplate sm;

	@Override
	public int getTotalCnt(BoardDto dto) {
		return sm.selectOne("Board_getTotalCnt", dto);
	}

	@Override
	public List<BoardDto> getList(BoardDto dto) {
		return sm.selectList("Board_getList", dto);
	}

	@Override
	public void insert(BoardDto dto) {
		sm.insert("Board_insert", dto);
	}

	@Override
	public BoardDto getView(BoardDto dto) {
		sm.update("Board_setHit", dto);
		return sm.selectOne("Board_getView", dto);

	}

	@Override
	public void update_level(BoardDto dto) {
		sm.update("Board_reply_level_update", dto);
	}

	@Override
	public void reply(BoardDto dto) {
		sm.insert("Board_reply", dto);
	}

	@Override
	public void modify(BoardDto dto) {
		sm.update("Board_mody", dto);
	}

	@Override
	public void delete(BoardDto dto) {
		
		// dto - group_id, depth, g_level 있어야 함
		System.out.println("Group_id: " + dto.getGroup_id());
		System.out.println("Depth: " + dto.getDepth());
		System.out.println("g_level: " + dto.getG_level());
		
		int cnt = sm.selectOne("Board_isChild",dto);
		if(cnt==0)
			sm.update("Board_delete1", dto);
		else
			sm.delete("Board_delete2", dto);
	}
}
