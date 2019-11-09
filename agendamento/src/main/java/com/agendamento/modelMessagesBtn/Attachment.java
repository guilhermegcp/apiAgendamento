package com.agendamento.modelMessagesBtn;

public class Attachment {
	
	private String type;	
	
	private Payload payload;
	

	public Attachment() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Attachment(String type, Payload payload) {
		super();
		this.type = type;
		this.payload = payload;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public Payload getPayload() {
		return payload;
	}


	public void setPayload(Payload payload) {
		this.payload = payload;
	}
	
	
	

}
