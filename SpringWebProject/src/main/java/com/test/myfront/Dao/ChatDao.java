package com.test.myfront.Dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.myfront.chatVo.ChatRoom;

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
}
