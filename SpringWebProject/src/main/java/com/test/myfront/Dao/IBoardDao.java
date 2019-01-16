package com.test.myfront.Dao;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.test.myfront.board.Board;
import com.test.myfront.member.Member;

public interface IBoardDao {
	int BoardWrite(Board board);
	List<Board> getList(Board board);
	
	List<Board> getDetailList(Board board, int cnt);
	int BoardModify(Board board, int cnt);
	List<Board> ShowDefaultModifyList(Board board, int cnt);
}