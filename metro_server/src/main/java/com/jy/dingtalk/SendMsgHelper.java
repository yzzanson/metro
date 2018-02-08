package com.jy.dingtalk;

import com.alibaba.fastjson.JSONObject;
import com.jy.dingtalk.entity.OaMsgEntity;
import com.jy.dingtalk.helper.AuthHelper;
import com.jy.dingtalk.message.SimpleOAMessage;
import com.jy.metro.bean.vo.TicketVO;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * 发钉消息工具类
 * Created by Saber on 2016/8/18.
 */
public class SendMsgHelper {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(com.jy.dingtalk.SendMsgHelper.class.getCanonicalName());

    public static Map<String,Object> localMap = new HashMap<>();
    /**
     * 发送OA消息
     *
     * @param config
     * @return
     */
    public static JSONObject sendOAMsgToDept(String title,TicketVO config,  Map<String, Object> map) {
        return buildAndSendOAMsg(title,config, map);
    }


    /**
     * 构造OA消息并发送
     *
     * @param config
     * @return
     */
    private static JSONObject buildAndSendOAMsg(String title,TicketVO config,  Map<String, Object> map) {

        JSONObject json = new JSONObject();

        SimpleOAMessage message = new SimpleOAMessage();
        message.setAccessToken(config.getAccessToken());
        message.setToUser(config.getDingId());
        message.setAgentId(config.getAgentId());
        //modify by nanzhou 2017-7-17
        message.setForm(map);
        message.setTitle(title);
        //message.setAuthor(author);
        String corpid = config.getCorpId();

        message.setCorpId(corpid);
        if (StringUtils.isNotEmpty(config.getDepartmentId())) {
            message.setToParty(config.getDepartmentId());
        }
        System.out.println("获取钉钉企业配置：" + config.toString());
                OaMsgEntity msgEntity = null;
        try {
            msgEntity = AuthHelper.sendOAReturn(message);
            System.out.println("钉消息发送结果:"+ msgEntity.toString());
            json.put("result", 0);
            json.put("msg", "发送成功");
            json.put("data", msgEntity);
            return json;
        } catch (Exception e) {
            System.out.println("发送钉钉企业消息失败：" + e.getMessage());
                    e.printStackTrace();
            json.put("result", 1);
            json.put("msg", e.getMessage());
            json.put("data", msgEntity);
            return json;
        }

    }



}
