package com.kosa.myapp3.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosa.myapp3.mapper.MemberMapper;

@Service("memberService")
public class MemberServiceImpl implements MemberService{
	@Autowired
	MemberMapper memberDao;

	@Override
	public void Member_insert(MemberDto dto) {
		// xml에서 지정한 member_insert 함수명으로 그래로 따라옴
		memberDao.Member_insert(dto);
	}
	
}
