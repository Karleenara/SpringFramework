package com.kosa.myapp3;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kosa.myapp3.board.BoardDao;
import com.kosa.myapp3.board.BoardDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })

public class BoardTest {

	@Resource(name="boardDao")
	BoardDao dao;
	
	@Test
	public void boardTest()
	{
		BoardDto dto = new BoardDto();
		System.out.println(dao.getTotalCnt(dto));
		List<BoardDto> list = dao.getList(dto);
		for(int i=0; i<list.size(); i++)
		{
			System.out.println(list.get(i).getTitle());
		}
	}
}