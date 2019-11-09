package com.agendamento.modelMesagesQuick;

public class QuickReplyOptions {
	
	private boolean process_text_by_ai;
	private String text_attribute_name;
	
	public QuickReplyOptions() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuickReplyOptions(boolean process_text_by_ai, String text_attribute_name) {
		super();
		this.process_text_by_ai = process_text_by_ai;
		this.text_attribute_name = text_attribute_name;
	}

	public boolean isProcess_text_by_ai() {
		return process_text_by_ai;
	}

	public void setProcess_text_by_ai(boolean process_text_by_ai) {
		this.process_text_by_ai = process_text_by_ai;
	}

	public String getText_attribute_name() {
		return text_attribute_name;
	}

	public void setText_attribute_name(String text_attribute_name) {
		this.text_attribute_name = text_attribute_name;
	}
	
	

}
