package com.jy.dingtalk.message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class MessageDelivery {
	
	public String msgtype;
	public Message message;
	
	public MessageDelivery withMessage(Message msg) {
		this.msgtype = msg.type();
		this.message = msg;
		return this;
	}
	
	public JSONObject toJsonObject() {
		JSONObject json = new JSONObject();
		json.put(this.msgtype, JSON.toJSON(this.message));
		json.put("msgtype", this.msgtype);
		return json;
	}
}
