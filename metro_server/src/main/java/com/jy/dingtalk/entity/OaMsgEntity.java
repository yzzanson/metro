package com.jy.dingtalk.entity;

import java.io.Serializable;
import java.util.Date;

public class OaMsgEntity implements Serializable {

	private Integer id;

	private Integer errCode;

	private String errMsg;

	private String invalidUser;

	private String invalidParty;

	private String messageId;


	private Date createdAt;

	private String content;


	public void setId(Integer id) {
		this.id=id;
	}

	public Integer getId(){
		return id;
	}

	public void setErrCode(Integer errCode) {
		this.errCode=errCode;
	}

	public Integer getErrCode(){
		return errCode;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg=errMsg;
	}

	public String getErrMsg(){
		return errMsg;
	}

	public void setInvalidUser(String invalidUser) {
		this.invalidUser=invalidUser;
	}

	public String getInvalidUser(){
		return invalidUser;
	}

	public void setInvalidParty(String invalidParty) {
		this.invalidParty=invalidParty;
	}

	public String getInvalidParty(){
		return invalidParty;
	}

	public void setMessageId(String messageId) {
		this.messageId=messageId;
	}

	public String getMessageId(){
		return messageId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
