package com.jy.dingtalk.message;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jy.dingtalk.exception.OApiException;
import com.jy.dingtalk.helper.AuthHelper;
import org.apache.commons.lang.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liam on 16/12/21.
 */
public class SimpleOAMessage {
    // 以下必须选一个,多个用|隔开
    private String toUser;
    private String toParty;


    // 方式a b  选其中一种即可
    // 方式a
    private String accessToken;
    private String agentId;
    // 方式b
    private String corpId;

    private String money;

    private String title;
    private String content;// 内容描述,不会换行

    private Map<String,Object > form;// 表单会自动换行

    private JSONArray formList;

    private String messageUrl;

    private String pcMessageUrl;

    private String author;

    private Map<String,Object> head;


    private JSONArray map2jsonArray(){
        JSONArray array = new JSONArray();
        JSONObject o = null;
        if(form != null){
            for(Map.Entry<String, Object> entry:form.entrySet()){
                o = new JSONObject();
                o.put("key",entry.getKey());
                o.put("value",entry.getValue());
                array.add(o);
            }
        }
        return array;
    }


    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getToParty() {
        return toParty;
    }

    public void setToParty(String toParty) {
        this.toParty = toParty;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Map<String, Object> getForm() {
        return form;
    }

    public void setForm(Map<String, Object> form) {
        this.form = form;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public JSONArray getFormList() {
        return formList;
    }

    public void setFormList(JSONArray formList) {
        this.formList = formList;
    }

    public String getMessageUrl() {
        return messageUrl;
    }

    public void setMessageUrl(String messageUrl) {
        this.messageUrl = messageUrl;
    }

    public String getPcMessageUrl() {
        return pcMessageUrl;
    }

    public void setPcMessageUrl(String pcMessageUrl) {
        this.pcMessageUrl = pcMessageUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Map<String, Object> getHead() {
        return head;
    }

    public void setHead(Map<String, Object> head) {
        this.head = head;
    }

    public JSONObject toJSONObject(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("touser", toUser);
        jsonObject.put("toparty", toParty);
        jsonObject.put("agentid", agentId );

        jsonObject.put("msgtype","oa");

        JSONObject oa = new JSONObject();
        oa.put("message_url", messageUrl);
        oa.put("pc_message_url",pcMessageUrl);
//        oa.put("message_url","http://www.baidu.com");
//        oa.put("pc_message_url","http://b.taofairy.com:9984/isvticket/toList");


        JSONObject head = new JSONObject();
        head.put("bgcolor","FF4876FF");
        head.put("text","头部标S题");// 设置没用。
        oa.put("head",head);

        JSONObject body = new JSONObject();
        body.put("title",title);
        body.put("author",author);

        if(formList != null){
            body.put("form",formList);
        }else{
            body.put("form",map2jsonArray());
        }

        if(StringUtils.isNotBlank(money)){
            JSONObject rich = new JSONObject();
            rich.put("num",money);
            rich.put("unit","元");
            body.put("rich",rich);
        }
        body.put("content",content);
//        body.put("author","liam");
        oa.put("body",body);
        jsonObject.put("oa",oa);

        System.out.println(jsonObject.toJSONString());
        return jsonObject;
    }


    public static void main(String[] args) {
        SimpleOAMessage simpleOAMessage = new SimpleOAMessage();
        simpleOAMessage.setToUser("1");
        simpleOAMessage.setAccessToken("b7da784e4be231378c9715ce36118e18");
        simpleOAMessage.setAgentId("37739324");
        simpleOAMessage.setTitle("网费充值");
        simpleOAMessage.setMoney("100.00");

        // map方便 但 无序
        Map<String,Object> map = new HashMap<>();
        map.put("手机号:","15257432547");
        map.put("身份证:","330383399474632111");
        map.put("充值时间:", new Date());
        simpleOAMessage.setForm(map);
        try {
            AuthHelper.sendOAReturn(simpleOAMessage);
        } catch (OApiException e) {
            e.printStackTrace();
        }
    }
}
