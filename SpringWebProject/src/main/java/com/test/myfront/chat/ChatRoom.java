package com.test.myfront.chat;

public class ChatRoom {
	private String chatuser;
	private String roomname;
	private String other;
	private int chatcnt;
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public String getChatuser() {
		return chatuser;
	}
	public void setChatuser(String chatuser) {
		this.chatuser = chatuser;
	}
	public String getRoomname() {
		return roomname;
	}
	public void setRoomname(String roomname) {
		this.roomname = roomname;
	}

	public int getChatcnt() {
		return chatcnt;
	}
	public void setChatcnt(int chatcnt) {
		this.chatcnt = chatcnt;
	}
}
