package com.test.myfront.Dao;

import com.test.myfront.member.Member;

public interface IMemberDao {
	int memberInsert(Member member);
	Member memberSearch(Member member);
}
