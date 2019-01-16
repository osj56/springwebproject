package com.test.myfront.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.myfront.Dao.BoardDao;
import com.test.myfront.board.Board;
import com.test.myfront.member.Member;

@Service
public class BoardService implements IBoardService {
	
	@Autowired
	BoardDao dao;
	
	@Override
	public void boardWrite(Board board) {
	//	HttpSession session = request.getSession();
		
		  dao.BoardWrite(board);
		
	}

	public List<Board> boardList(Board board){
	  return dao.getList(board);
	}
	public List<Board> detailBoardList(Board board,int cnt){
		//System.out.println(cnt);
		return dao.getDetailList(board,cnt);
	}
	public List<Board> showDefaultModify(Board board,int cnt){
		//System.out.println(cnt);
		return dao.getDetailList(board,cnt);
	}

	@Override
	public Board boardModify(Board board, int cnt) {
		// TODO Auto-generated method stub
		dao.BoardModify(board, cnt);
		System.out.println("¼·½Ã½º");
		System.out.println(cnt);
		return board;
	}

	
	
	
}
