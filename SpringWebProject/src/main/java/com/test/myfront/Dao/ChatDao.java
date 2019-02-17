package com.test.myfront.Dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.myfront.board.Board;
import com.test.myfront.board.Criteria;
import com.test.myfront.chat.ChatRoom;

@Repository
public class ChatDao {
	@Autowired
	private SqlSession sqlsession;
	
	public void insertChatRoom(final ChatRoom room) {
		 sqlsession.insert("chat.chatRoom", room);
	}
	
	public List<ChatRoom> getRoom(final ChatRoom room){
		return sqlsession.selectList("chat.getList");
	}
	
	public List<String> getUser(String roomname) {
		return sqlsession.selectList("chat.getUser", roomname);
	
	}
	
	public List<ChatRoom> listCriteria(Criteria cri) {
		// TODO Auto-generated method stub
		return sqlsession.selectList("chat.listCriteria", cri);
	}
	public int countPaging(Criteria cri) {
		// TODO Auto-generated method stub
		return sqlsession.selectOne("chat.countPaging", cri);
	}

}
