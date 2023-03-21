package com.kosa.myapp1.score;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreServiceImpl implements ScoreService{
	@Autowired
	ScoreDao scoreDao;

	@Override
	public List<ScoreDto> getList(ScoreDto dto) {
		// TODO Auto-generated method stub
		return scoreDao.getList(dto);
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
