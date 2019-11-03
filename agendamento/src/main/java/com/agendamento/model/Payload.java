package com.agendamento.model;

import java.util.List;

public class Payload {
	
	private String template_type;
	
	private String text;
	
	private List<Buttons> buttons;

	public Payload() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Payload(String template_type, String text, List<Buttons> buttons) {
		super();
		this.template_type = template_type;
		this.text = text;
		this.buttons = buttons;
	}

	public String getTemplate_type() {
		return template_type;
	}

	public void setTemplate_type(String template_type) {
		this.template_type = template_type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<Buttons> getButtons() {
		return buttons;
	}

	public void setButtons(List<Buttons> buttons) {
		this.buttons = buttons;
	}
	
	

}
