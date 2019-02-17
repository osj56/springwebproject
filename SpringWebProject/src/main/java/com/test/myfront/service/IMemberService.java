package com.test.myfront.service;

import com.test.myfront.member.Member;

public interface IMemberService {
	void memberRegister(Member member);
	Member memberSearch(Member member);
}
