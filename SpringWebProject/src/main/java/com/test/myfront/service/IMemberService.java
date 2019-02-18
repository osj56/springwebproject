package com.test.myfront.service;

import java.util.List;

import com.test.myfront.member.Member;

public interface IMemberService {
	void memberRegister(Member member);
	Member memberSearch(Member member);
	List<Member> clientInfo(Member member);
	void memberModify(Member member);
	void memberDelete(Member member);
}
