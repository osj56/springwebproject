package com.test.myfront.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.myfront.Dao.MemberDao;
import com.test.myfront.member.Member;
@Service
public class MemberService implements IMemberService {
	@Autowired
	MemberDao dao;
	@Override
	public void memberRegister(Member member) {
		int result = dao.memberInsert(member);
		if(result==0)System.out.println("join Fail");
		else System.out.println("join succes");

	}
	@Override
	public Member memberSearch(Member member) {
		
		Member result = dao.memberSearch(member);
		
		return result;
		
	}
	@Override
	public List<Member> clientInfo(Member member){
		List<Member> mem = dao.ClientInfo(member);
		return mem;
	}
	@Override
	public void memberModify(Member member) {
		dao.MemberModify(member);
	}
	@Override
	public void memberDelete(Member member){
		dao.memberDelete(member);
	}
}
