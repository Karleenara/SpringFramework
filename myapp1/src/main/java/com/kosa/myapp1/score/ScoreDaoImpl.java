package com.kosa.myapp1.score;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository("ScoreDao")
public class ScoreDaoImpl implements ScoreDao {
	List<ScoreDto> list = new ArrayList<ScoreDto>();
	
	public ScoreDaoImpl() {
		super();
		list.add(new ScoreDto("홍길동", "100", "99", "98"));
		list.add(new ScoreDto("김똥깡", "80", "59", "86"));
		list.add(new ScoreDto("주길동", "70", "99", "75"));
	}

	@Override
	public List<ScoreDto> getList(ScoreDto dto) {
		// TODO Auto-generated method stub
		return list;
	}

	@Override
	public ScoreDto getView(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(ScoreDto dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(ScoreDto dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(ScoreDto dto) {
		// TODO Auto-generated method stub
		
	}
	
}
