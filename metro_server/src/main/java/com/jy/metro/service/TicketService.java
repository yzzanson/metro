package com.jy.metro.service;

import com.alibaba.fastjson.JSONObject;
import com.jy.dingtalk.SendMsgHelper;
import com.jy.dingtalk.helper.AuthHelper;
import com.jy.metro.bean.vo.TicketVO;
import com.jy.metro.common.Constant;
import com.jy.metro.util.DateUtil;
import com.jy.metro.util.ResultJson;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

/**
 * Created by anson on 18/2/7.
 */
public class TicketService {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(TicketService.class.getCanonicalName());


    public JSONObject refeshTicket(){
        Map<String,Object> localTicketMap = SendMsgHelper.localMap;
        if(!localTicketMap.containsKey("ticket")){
            return dorefreshTicket(localTicketMap);
        }else{
            TicketVO ticketVO = (TicketVO) SendMsgHelper.localMap.get(Constant.TICKET_KEY);
            if(ticketVO.getUpdatedAt()!=null && DateUtil.getMinutesBetweenTwoDate(ticketVO.getUpdatedAt(), DateUtil.getCurrentTime()) >= 110){
                return dorefreshTicket(localTicketMap);
            }else if(ticketVO.getUpdatedAt()!=null && DateUtil.getMinutesBetweenTwoDate(ticketVO.getUpdatedAt(), DateUtil.getCurrentTime()) < 110){
                return ResultJson.succResultJson(ticketVO);
            }
        }
        return dorefreshTicket(localTicketMap);
    }

    private JSONObject dorefreshTicket(Map<String,Object> localTicketMap){
        TicketVO ticketVO = null;
       if(localTicketMap!=null){
           ticketVO = (TicketVO) localTicketMap.get(Constant.TICKET_KEY);
           if(ticketVO!=null && StringUtils.isNotEmpty(ticketVO.getCorpId()) && StringUtils.isNotEmpty(ticketVO.getAccessToken())){
                ticketVO = refreshVO(ticketVO);
           }else{
               ticketVO = loadProperties();
               ticketVO = refreshVO(ticketVO);
           }
       }else{
           ticketVO = loadProperties();
           ticketVO = refreshVO(ticketVO);
       }
        return ResultJson.succResultJson(ticketVO);
    }

    private TicketVO refreshVO(TicketVO ticketvo){
        String accessToken = null;
        try {
            accessToken = AuthHelper.getAccessToken(ticketvo.getCorpId(), ticketvo.getSecrect());
            String ticket = AuthHelper.getJsapiTicket(accessToken);
            ticketvo.setAccessToken(accessToken);
            ticketvo.setTicket(ticket);
            //执行更新
            ticketvo.setUpdatedAt(DateUtil.getCurrentTime());
            logger.info("刷新ticket,更新后的数据:corpid:"+ticketvo.getCorpId()+"   corpSecrect:"+ticketvo.getSecrect()+"    更新时间:"+new Date() + "     更新后token:"+accessToken);
            SendMsgHelper.localMap.put(Constant.TICKET_KEY, ticketvo);
            return ticketvo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private TicketVO loadProperties(){
        Properties properties = null;
        TicketVO ticketvo = new TicketVO();
        try {
            properties = PropertiesLoaderUtils.loadAllProperties("ticket.properties");
            String corpid = (String) properties.get("corpid");
            String corpsecrect = (String) properties.get("corpsecrect");
            String agentid = (String) properties.get("agentid");
            String departmentid = (String) properties.get("departmentid");

            ticketvo.setCorpId(corpid);
            ticketvo.setSecrect(corpsecrect);
            ticketvo.setAgentId(agentid);
            ticketvo.setDepartmentId(departmentid);
            SendMsgHelper.localMap.put(Constant.TICKET_KEY,ticketvo);
        } catch (IOException e) {
            e.printStackTrace();
        }
       return ticketvo;
    }

}
