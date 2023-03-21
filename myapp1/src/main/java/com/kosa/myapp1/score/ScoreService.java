package com.kosa.myapp1.score;

import java.util.List;

public interface ScoreService {
	List<ScoreDto> getList(ScoreDto dto);
	ScoreDto getView(String name);
	void insert(ScoreDto dto);
	void update(ScoreDto dto);
	void delete(ScoreDto dto);
	
}
