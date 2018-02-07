package com.jy.metro.thread;

import com.jy.dingtalk.helper.AuthHelper;
import com.jy.dingtalk.threadMsg.ThreadSendMsg;
import com.jy.metro.bean.vo.TicketVO;
import org.junit.Test;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by anson on 18/2/7.
 */
public class ThreadSendMsgTest {

    @Test
    public void sendOAMessage() {
        String corpid = "ding38c46ef492978e7335c2f4657eb6378f";
        String corp_secrect = "vwjGdD4rDr-pUE0ej0rzo63ktCC6v2VnFIc3rexpu3-N2C46xXCoTVwje2ZRtiT6";
        String agentid = "161969764";
        String departmentid = "59828702";
        try {
            String accessToken = AuthHelper.getAccessToken(corpid, corp_secrect);
            System.out.println("accessToken:"+accessToken);
            TicketVO ticketVO = new TicketVO();
            ticketVO.setUpdatedAt(new Date());
            ticketVO.setAgentId(agentid);
            ticketVO.setCorpId(corpid);
            ticketVO.setSecrect(corp_secrect);
            ticketVO.setDepartmentId(departmentid);
            ticketVO.setAccessToken(accessToken);
            Map<String, Object> oaMap = buildSendMessageMap();
            ThreadSendMsg threadSendMsg = new ThreadSendMsg(ticketVO,"重大施工提醒计划",oaMap);
            threadSendMsg.run();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Map<String, Object> buildSendMessageMap() {
        Map<String, Object> oaSendMap = new LinkedHashMap<>();
        oaSendMap.put("线路名称:", "杭州地铁二号线");
        oaSendMap.put("施工时间:", "2018-02-06 00:00:00 -  2018-02-07 01:22:00");
        oaSendMap.put("申报单位:", "杭州市规划局");
        oaSendMap.put("施工单位:", "杭州地铁二建");
        oaSendMap.put("施工内容:", "学院路口段到武林广场段打通");
        oaSendMap.put("", "该施工计划已批复");
        return oaSendMap;
    }
}
