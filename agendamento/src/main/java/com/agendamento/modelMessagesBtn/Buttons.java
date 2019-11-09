package com.agendamento.modelMessagesBtn;

import java.util.List;

public class Buttons {
	
	private String type;
	private List<String> block_names; 
	private String title;
	private String url;

	public Buttons() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Buttons(String type, List<String> block_names, String title, String url) {
		super();
		this.type = type;
		this.block_names = block_names;
		this.title = title;
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getBlock_names() {
		return block_names;
	}

	public void setBlock_names(List<String> block_names) {
		this.block_names = block_names;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
}
