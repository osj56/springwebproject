package com.test.myfront.chat;

public class Message {
	private String from;
	private String text;
/*	public Message(String string, String string2, String time) {
		// TODO Auto-generated constructor stub
		setFrom(string);
		setText(string2);
	}
	*/
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
}
