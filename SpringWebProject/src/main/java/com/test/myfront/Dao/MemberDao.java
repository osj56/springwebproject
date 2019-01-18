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
/*	private String driver = "oracle.jdbc.driver.OracleDriver";s
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String userid = "scott";
	private String userpw = "tiger";
	
	private ComboPooledDataSource dataSource;
	
	private JdbcTemplate template;
	*/
	
	@Autowired
	private SqlSession sqlsession;
	
/*	public MemberDao() {
		dataSource = new ComboPooledDataSource();
		try {
			dataSource.setDriverClass(driver);
			dataSource.setJdbcUrl(url);
			dataSource.setUser(userid);
			dataSource.setPassword(userpw);
		}catch(PropertyVetoException e) {
			e.printStackTrace();
		}
		template=new JdbcTemplate();
		template.setDataSource(dataSource);
	}
	*/
	@Override
	public int memberInsert(final Member member) {
		// TODO Auto-generated method stub
	/*	int result=0;
		final String sql = "INSERT INTO member (memId,memPw, memMail) values (?,?,?)";
		result = template.update(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				ps.setString(1, member.getMemId());
				ps.setString(2, member.getMemPw());
				ps.setString(3, member.getMemMail());
			}
			
		});
		*/
		return sqlsession.insert("member.memberInsert", member);
	}
	
	@Override
	public Member memberSearch(final Member member) {
		// TODO Auto-generated method stub
		List<Member> members=null;
	/*	final String sql="SELECT * FROM member WHERE memId = ? AND memPw = ?";
		
		members=template.query(sql, new Object[] {member.getMemId(),member.getMemPw()},new RowMapper<Member>() {

			@Override
			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Member mem = new Member();
				mem.setMemId(rs.getString("memId"));
				mem.setMemPw(rs.getString("memPw"));
				mem.setMemMail(rs.getString("memMail"));
				return mem;
			}
			
		});
		*/
		members = sqlsession.selectList("member.memberSearch", member);
		if(members.isEmpty()) return null;
		return members.get(0);
	}
	
	public List<Member> ClientInfo(final Member member) {
		// TODO Auto-generated method stub
		List<Member> members=null;
		/*final String sql="SELECT * FROM member WHERE memId = ?";
		
		members=template.query(sql, new Object[] {member.getMemId()},new RowMapper<Member>() {

			@Override
			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Member mem = new Member();
				mem.setMemId(rs.getString("memId"));
				mem.setMemPw(rs.getString("memPw"));
				mem.setMemMail(rs.getString("memMail"));
				return mem;
			}
			
		});
		*/
		members = sqlsession.selectList("member.ClientInfo", member);
		if(members.isEmpty()) return null;
		return members;
	}


}
