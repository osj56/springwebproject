package com.test.myfront.Dao;

import java.beans.PropertyVetoException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.test.myfront.member.Member;
@Repository
public class MemberDao implements IMemberDao {
	
	@Autowired
	private SqlSession sqlsession;

	@Override
	public int memberInsert(final Member member) {
		// TODO Auto-generated method stub

		return sqlsession.insert("member.memberInsert", member);
	}
	
	@Override
	public Member memberSearch(final Member member) {
		// TODO Auto-generated method stub
		List<Member> members=null;
	
		members = sqlsession.selectList("member.memberSearch", member);
		if(members.isEmpty()) return null;
		return members.get(0);
	}
	
	public List<Member> ClientInfo(final Member member) {
		// TODO Auto-generated method stub
		List<Member> members=null;
		
		members = sqlsession.selectList("member.ClientInfo", member);
		if(members.isEmpty()) return null;
		return members;
	}

	public void MemberModify(final Member member) {
		sqlsession.update("member.MemberModify", member);
	}
	
	public void memberDelete(final Member member) {
		sqlsession.delete("member.memberDelete", member);
	}
}
