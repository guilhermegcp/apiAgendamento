package com.agendamento.model;

import java.util.List;

public class Messages {
	
	private List<Text> messages;
	
	public List<Text> getMessages() {
		return messages;
	}

	public void setMessages(List<Text> messages) {
		this.messages = messages;
	}

	public Messages(List<Text> messages, List<Attachment> attachment) {
		super();
		this.messages = messages;
	}

	public Messages() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}