package com.test.myfront.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.myfront.Dao.MemberDao;
import com.test.myfront.member.Member;
@Service
public class RMemberService implements MemberService {
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
		// TODO Auto-generated method stub
		Member result = dao.memberSearch(member);
		if (result == null) {
			System.out.println("Login Fail!!");
		} else {
			System.out.println("Login Success!!");
		}
		return result;
		
	}

}