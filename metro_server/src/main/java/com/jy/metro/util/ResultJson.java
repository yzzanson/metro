package com.jy.metro.util;

import com.alibaba.fastjson.JSONObject;
import com.jy.metro.enums.ResultCodeEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * ResultJson
 *
 * @author shisan
 * @create 2017-07-20 上午11:51
 **/
public class ResultJson {

    /**
     * 结果码
     */
    private String result;

    /**
     * 消息
     */
    private String msg = "";

    private Map<String, Object> data;

    public ResultJson() {
    }

    public ResultJson(String result) {
        this.result = result;
    }

    public static JSONObject succResultJson(String msg, Map<String, Object> data) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", ResultCodeEnum.SUCCESS.getValue().toString());
        jsonObject.put("msg", msg);
        jsonObject.put("data", data);

        return jsonObject;
    }

    public static JSONObject succResultJson(Map<String, Object> data) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", ResultCodeEnum.SUCCESS.getValue().toString());
        jsonObject.put("msg", "");
        jsonObject.put("data", data);
        return jsonObject;
    }

    public static JSONObject succResultJson(Object value) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", ResultCodeEnum.SUCCESS.getValue().toString());
        jsonObject.put("msg", "ok");
        jsonObject.put("data", value);
        return jsonObject;
    }

    public static JSONObject succResultJson(String key, Object value) {
        Map<String, Object> data = new HashMap<>();
        data.put(key, value);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", ResultCodeEnum.SUCCESS.getValue().toString());
        jsonObject.put("msg", "");
        jsonObject.put("data", data);
        return jsonObject;
    }

    public static JSONObject succResultJson2(String msg, Object value) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", ResultCodeEnum.SUCCESS.getValue().toString());
        jsonObject.put("msg", "");
        jsonObject.put("data", value);
        return jsonObject;
    }

    public static JSONObject errorResultJson(String msg) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", ResultCodeEnum.FAIL.getValue());
        jsonObject.put("msg", msg);
        jsonObject.put("data", new HashMap<>());
        return jsonObject;
    }

    public static JSONObject errorResultJson(Object msg) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", ResultCodeEnum.FAIL.getValue());
        jsonObject.put("msg", "fail");
        jsonObject.put("data", msg);
        return jsonObject;
    }

    public static JSONObject errorResultJson(Map<String, Object> data) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", ResultCodeEnum.FAIL.getValue().toString());
        jsonObject.put("msg", "");
        jsonObject.put("data", data);
        return jsonObject;
    }

    public static JSONObject errorResultJson(String msg, Object value) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", ResultCodeEnum.FAIL.getValue().toString());
        jsonObject.put("msg", msg);
        jsonObject.put("data", value);
        return jsonObject;
    }


    public static JSONObject noPermissionResultJson(String msg) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", ResultCodeEnum.NO_PERMISSION.getValue());
        jsonObject.put("msg", msg);
        jsonObject.put("data", new HashMap<>());
        return jsonObject;
    }

    public static JSONObject kickedutResultJson(String msg) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", ResultCodeEnum.KICKED_OUT.getValue());
        jsonObject.put("msg", msg);
        jsonObject.put("data", new HashMap<>());
        return jsonObject;
    }

    /**
     * crm未登录时返回信息
     *
     * @param msg 返回消息
     * @author shisan
     * @date 2017/10/11 下午7:37
     */
    public static JSONObject hasCrmLogin(String msg) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", ResultCodeEnum.CRM_NOT_LOGIN.getValue());
        jsonObject.put("msg", msg);
        jsonObject.put("data", new HashMap<>());
        return jsonObject;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
