package com.test.myfront.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.test.myfront.board.Board;
import com.test.myfront.member.Member;

public interface IBoardService {
	void boardWrite(Board board);
	List<Board> detailBoardList(Board board,int cnt);
	Board boardModify(Board board, int cnt);
}
