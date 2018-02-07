package com.jy.dingtalk.constant;



/**
 * Created by houminglong on 16/8/9.
 */
public class Contanst {
    public static final String OAPI_HOST = "https://oapi.dingtalk.com";

    public static final String CORP_ID = "corpid";
    public static final String CORP_SECRET ="corpsecret";
    public static final String ACCESS_TOKEN = "access_token";

    //获取 access_token url
    public static final String GET_ACCESS_TOKEN_URL = OAPI_HOST+"/gettoken?1=1";
    //获取 ticket url
    public static final String GET_JSAPI_TICKET_URL = OAPI_HOST+"/get_jsapi_ticket?1=1";
}

