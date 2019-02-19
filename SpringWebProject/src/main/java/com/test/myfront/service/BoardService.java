package com.test.myfront.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.myfront.Dao.BoardDao;
import com.test.myfront.board.Board;
import com.test.myfront.board.Criteria;

@Service
public class BoardService implements IBoardService {
	
	@Autowired
	BoardDao dao;
	
	@Override
	public void boardWrite(Board board) {
	
		  dao.BoardWrite(board);
		
	}
	
	@Override
	public List<Board> boardList(Board board){
	  return dao.getList(board);
	}
	
	@Transactional
	@Override
	public List<Board> detailBoardList(Board board,int cnt){
		
		dao.BoardViewCnt(cnt);
		
		return dao.getDetailList(board,cnt);
	}
	@Override
	public List<Board> showDefaultModify(Board board,int cnt){
		
		return dao.getDetailList(board,cnt);
	}

	@Override
	public Board boardModify(Board board, int cnt) {
		// TODO Auto-generated method stub
		dao.BoardModify(board, cnt);
		
		return board;
	}

	@Override
	public void boardDelete(Board board, int cnt) {
		// TODO Auto-generated method stub
		dao.BoardDelete(board, cnt);
	}

	@Override
	public List<Board> listCriteria(Criteria cri) {
		// TODO Auto-generated method stub
		return dao.listCriteria(cri);
	}

	@Override
	public int listCountCriteria(Criteria cri) {
		// TODO Auto-generated method stub
		return dao.countPaging(cri);
	}

	@Override
	public int countPaging(Criteria cri) {
		// TODO Auto-generated method stub
		return dao.countPaging(cri);
	}
	
	
	
	
	
}
