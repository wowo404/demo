package org.demo.json;

import com.alibaba.fastjson.JSONObject;

public class Message {

	private Long id;

	private MessageType<?> type;
	
	private JSONObject data;

	public Message(MessageType<?> type, JSONObject data) {
		this.type = type;
		this.data = data;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MessageType<?> getType() {
		return type;
	}

	public void setType(MessageType<?> type) {
		this.type = type;
	}

	public JSONObject getData() {
		return data;
	}

	public void setData(JSONObject data) {
		this.data = data;
	}
}
