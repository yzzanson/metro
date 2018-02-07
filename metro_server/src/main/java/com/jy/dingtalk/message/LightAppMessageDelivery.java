package com.jy.dingtalk.message;
import com.alibaba.fastjson.JSONObject;

public class LightAppMessageDelivery extends MessageDelivery {

	public String touser;
	public String toparty;
	public String agentid;


	public LightAppMessageDelivery(String touser, String toparty, String agentid) {
		this.touser = touser;
		this.toparty = toparty;
		this.agentid = agentid;
	}

	@Override
	public JSONObject toJsonObject() {
		JSONObject json = super.toJsonObject();
		json.put("touser", this.touser);
		json.put("toparty", this.toparty);
		json.put("agentid", this.agentid);
		
		return json;
	}
}
