package com.kosa.myapp3.board;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository("boardDao")
public class BoardDaoImpl implements BoardDao{

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
		//조회수증가후 데이터 가져가는 코드 추가하기 
		sm.update("Board_updateHit", dto);
		return sm.selectOne("Board_getView", dto);
	}

	@Override
	public void reply(BoardDto dto) {
		
		sm.insert("Board_reply", dto);
	}

	@Override
	public void update_level(BoardDto dto) {
		
		sm.update("Board_reply_level_update", dto);
		
	}

	@Override
	public void update(BoardDto dto) {
		sm.update("Board_update", dto);
	}

	@Override
	public void delete(BoardDto dto) {
		
		//dto - group_id, depth, g_level
		System.out.println("그룹아이디 : " + dto.getGroup_id());
		System.out.println("깊이 : " + dto.getDepth());
		System.out.println("레벨 : " + dto.getG_level());
		
		int cnt = sm.selectOne("Board_isChild", dto);
		if(cnt==0) //글을 삭제 
			sm.update("Board_delete1", dto);
		else //자식이 있을경우 delyn2 필드값만 삭제 표시를 한다 
			sm.update("Board_delete2", dto);
	}

}





