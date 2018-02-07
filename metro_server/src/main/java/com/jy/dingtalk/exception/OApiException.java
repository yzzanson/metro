package com.jy.dingtalk.exception;

public class OApiException extends Exception {

	public OApiException(int errCode, String errMsg) {
		super("error code: " + errCode + ", error message: " + errMsg);
	}
}
