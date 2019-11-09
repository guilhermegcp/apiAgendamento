package com.agendamento.modelMesagesQuick;

import java.util.List;

public class MessagesQuick {

	private String text;
	private List<QuickReplie> quick_replies;
	private QuickReplyOptions quick_reply_options;
	
	public MessagesQuick() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MessagesQuick(String text, List<QuickReplie> quick_replies, QuickReplyOptions quick_reply_options) {
		super();
		this.text = text;
		this.quick_replies = quick_replies;
		this.quick_reply_options = quick_reply_options;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<QuickReplie> getQuick_replies() {
		return quick_replies;
	}

	public void setQuick_replies(List<QuickReplie> quick_replies) {
		this.quick_replies = quick_replies;
	}

	public QuickReplyOptions getQuick_reply_options() {
		return quick_reply_options;
	}

	public void setQuick_reply_options(QuickReplyOptions quick_reply_options) {
		this.quick_reply_options = quick_reply_options;
	}
	
	
}
