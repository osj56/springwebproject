package com.test.myfront.Dao;

import java.beans.PropertyVetoException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.test.myfront.board.Board;
import com.test.myfront.member.Member;

@Repository
public class BoardDao implements IBoardDao {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String userid = "scott";
	private String userpw = "tiger";
	
	private ComboPooledDataSource dataSource;
	private JdbcTemplate template;
	
	public BoardDao() {
		dataSource = new ComboPooledDataSource();
		try {
			dataSource.setDriverClass(driver);
			dataSource.setJdbcUrl(url);
			dataSource.setUser(userid);
			dataSource.setPassword(userpw);
		}catch(PropertyVetoException e) {
			e.printStackTrace();
		}
		template = new JdbcTemplate();
		template.setDataSource(dataSource);
	}

	@Override
	public int BoardWrite(final Board board) {
	
		int result=0;
	
		final String sql="INSERT INTO board (title, boardcontent, writer, cnt) values (?,?,?,cnt.NEXTVAL)";
	
		result = template.update(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
		
				ps.setString(1, board.getTitle());
				ps.setString(2, board.getContent());
				ps.setString(3, board.getWriter());
			}
			
		});
		return result;
	//	System.out.println(member.getMemId());
	}

	@Override
	public List<Board> getList(final Board board) {
		// TODO Auto-generated method stub
		List<Board> boards = null;
		
		boards=template.query("SELECT title,boardcontent,writer,cnt FROM board", new RowMapper<Board>(){

			@Override
			public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Board board = new Board();
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("boardcontent"));
				board.setWriter(rs.getString("writer"));
				board.setCnt(rs.getInt("cnt"));
				return board;
			}
			
		});
	//	System.out.println(boards.);
		return boards;
	}

	@Override
	public List<Board> getDetailList(final Board board ,int cnt) {
		// TODO Auto-generated method stub
	//	final Board board 
		List<Board> boards=null;
		boards=template.query("SELECT title,boardcontent,writer,cnt FROM board WHERE cnt = ?",new Object[] {board.getCnt()},new RowMapper<Board>() {

			@Override
			public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Board board = new Board();
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("boardcontent"));
				board.setWriter(rs.getString("writer"));
				board.setCnt(rs.getInt("cnt"));
				return board;
			}
			
		});
		System.out.println(boards);
		return boards;
	}
	@Override
	public List<Board> ShowDefaultModifyList(final Board board ,int cnt) {
		// TODO Auto-generated method stub
	//	final Board board 
		List<Board> boards=null;
		boards=template.query("SELECT title,boardcontent,writer,cnt FROM board WHERE cnt = ?",new Object[] {board.getCnt()},new RowMapper<Board>() {

			@Override
			public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Board board = new Board();
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("boardcontent"));
				board.setWriter(rs.getString("writer"));
				board.setCnt(rs.getInt("cnt"));
				return board;
			}
			
		});
		System.out.println(boards);
		return boards;
	}
	@Override
	public int BoardModify(final Board board, int cnt) {
		int result=0;
		final String sql = "UPDATE board SET title = ?, boardcontent = ? WHERE cnt = ?";
		result = template.update(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				ps.setString(1, board.getTitle());
				ps.setString(2, board.getContent());
				ps.setInt(3, board.getCnt());
			}
			
		});
		return result;
	}
	
}
