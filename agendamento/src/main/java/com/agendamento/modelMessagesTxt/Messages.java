package com.agendamento.modelMessagesTxt;

import java.util.List;

import com.agendamento.modelMessagesBtn.Attachment;

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