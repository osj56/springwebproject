package com.test.myfront.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.myfront.Dao.ChatDao;
import com.test.myfront.board.Board;
import com.test.myfront.board.Criteria;
import com.test.myfront.chatVo.ChatRoom;

@Service
public class ChatService {
	@Autowired
	ChatDao dao;
	
	public void insertChatRoom(ChatRoom room) {
		dao.insertChatRoom(room);
	}
	
	public List<ChatRoom> getRoom(ChatRoom room){
		return dao.getRoom(room);
	}
	
	public List<String> getUser(String roomname) {
		return dao.getUser(roomname);
	}
	
	public List<ChatRoom> listCriteria(Criteria cri) {
		// TODO Auto-generated method stub
		return dao.listCriteria(cri);
	}
	
	public int listCountCriteria(Criteria cri) {
		// TODO Auto-generated method stub
		return dao.countPaging(cri);
	}

}
