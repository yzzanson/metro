package com.jy.metro.controller;

import com.alibaba.fastjson.JSONObject;
import com.jy.dingtalk.SendMsgHelper;
import com.jy.metro.bean.ConstructPlan;
import com.jy.metro.bean.vo.TicketVO;
import com.jy.metro.common.Constant;
import com.jy.metro.job.ConstructPushJob;
import com.jy.metro.service.JxfMonitor;
import com.jy.metro.service.TicketService;
import com.jy.metro.util.DateUtil;
import com.jy.metro.util.ResultJson;
import com.jy.metro.util.WebServiceUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * WebServiceMetroController
 *
 * @author anson
 * @create 2018-02-07 下午9:13
 **/
@Controller
@RequestMapping("/construct")
public class WebServiceMetroController {


    @ResponseBody
    @RequestMapping("/getConstruction")
    public JSONObject getConstruction() {
        JSONObject jsonData = new JSONObject();
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("arg0", "all");
        Long startTime = DateUtil.getDateEndDateTime(new Date()).getTime();
        Long endTime = DateUtil.getDateEndDateTime(new Date()).getTime();
        String result = WebServiceUtil.pushMethod(JxfMonitor.REMOTE_ADDR, "getConstructions", paramsMap);
        JSONObject jsonObject = JSONObject.parseObject(result);
        List<ConstructPlan> alarmPlan = new ArrayList<>();
        if(jsonObject.getString("resultCode").equals("00")){
            String jsonArrayStr1 = jsonObject.get("data").toString();
            List<ConstructPlan> constructPlanList = ConstructPushJob.getListFromJsonArray(jsonArrayStr1);
            //解析
            for (ConstructPlan constructPlan:constructPlanList){
                if(constructPlan.getPlanStartTime()>=startTime && constructPlan.getPlanStartTime()<=endTime){
                    alarmPlan.add(constructPlan);
                }
            }
        }
        return ResultJson.succResultJson(alarmPlan);
    }

    @ResponseBody
    @RequestMapping("/getTicket")
    public JSONObject getTicket() {
        TicketVO ticketVO = (TicketVO) SendMsgHelper.localMap.get(Constant.TICKET_KEY);
        return ResultJson.succResultJson(ticketVO);
    }

    @ResponseBody
    @RequestMapping("/refreshToken")
    public JSONObject refreshToken() {
        TicketService ticketService = new TicketService();
        return ticketService.refeshTicket();
    }

}
