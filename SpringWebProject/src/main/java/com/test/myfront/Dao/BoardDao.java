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
import com.test.myfront.board.Board;
import com.test.myfront.board.Criteria;

@Repository
public class BoardDao implements IBoardDao {

	@Autowired
	private SqlSession sqlsession;
	

	@Override
	public int BoardWrite(final Board board) {
	
		return sqlsession.insert("board.BoardWrite", board);
	
	}

	@Override
	public List<Board> getList(final Board board) {
		// TODO Auto-generated method stub
	
		return sqlsession.selectList("board.getList");
	}

	@Override
	public List<Board> getDetailList(final Board board ,int cnt) {
		// TODO Auto-generated method stub
	
		List<Board> boards=null;

		boards = sqlsession.selectList("board.getDetailList", cnt);
		
		return boards;
	}
	@Override
	public List<Board> ShowDefaultModifyList(final Board board ,int cnt) {
		// TODO Auto-generated method stub
	
		return sqlsession.selectList("board.ShowDefaultModifyList");
	}
	@Override
	public int BoardModify(final Board board, int cnt) {
	
		return sqlsession.update("board.BoardModify",board);
	}

	@Override
	public int BoardDelete(final Board board, int cnt) {

		// TODO Auto-generated method stub
		return sqlsession.delete("board.BoardDelete", board);
	}

	@Override
	public void BoardViewCnt(int cnt) {
	
		 sqlsession.update("board.BoardViewCnt", cnt);
	}
	
	@Override
	public List<Board> listPage(int page) {
		if(page<0) page = 1;
		page = (page - 1) * 10;
		return sqlsession.selectList("board.listPage",page);
	}
	
	@Override
	public List<Board> listCriteria(Criteria cri) {
		// TODO Auto-generated method stub
		return sqlsession.selectList("board.listCriteria", cri);
	}

	@Override
	public int countPaging(Criteria cri) {
		// TODO Auto-generated method stub
		return sqlsession.selectOne("board.countPaging", cri);
	}

	
	
}
