package com.jy.metro.controller;

import com.alibaba.fastjson.JSONObject;
import com.jy.dingtalk.SendMsgHelper;
import com.jy.dingtalk.threadMsg.ThreadSendMsg2;
import com.jy.metro.bean.ConstructPlan;
import com.jy.metro.bean.vo.TicketVO;
import com.jy.metro.common.Constant;
import com.jy.metro.job.ConstructPushJob;
import com.jy.metro.service.JxfMonitor;
import com.jy.metro.service.TicketService;
import com.jy.metro.util.ResultJson;
import com.jy.metro.util.WebServiceUtil;
import org.apache.commons.lang.StringUtils;
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
    @RequestMapping("/getConstruction_test")
    public JSONObject getConstruction_test() {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("arg0", "all");

        List<ConstructPlan> alarmPlan = new ArrayList<>();
        try {
            String result = WebServiceUtil.push(JxfMonitor.REMOTE_ADDR, "getConstructions", paramsMap);
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (jsonObject.getString("resultCode").equals("00")) {
                String jsonArrayStr1 = jsonObject.get("data").toString();
                List<ConstructPlan> constructPlanList = ConstructPushJob.getListFromJsonArray(jsonArrayStr1);
                //解析
    //            for (ConstructPlan constructPlan:constructPlanList){
    //                if(constructPlan.getPlanStartTime()>=startTime && constructPlan.getPlanStartTime()<=endTime){
    //                    alarmPlan.add(constructPlan);
    //                }
    //            }
                alarmPlan = constructPlanList;
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultJson.errorResultJson("");
        }
        return ResultJson.succResultJson(alarmPlan);
    }

    /**
     * 获取线路对应的的数据
     * 线路为空则取全部
     */
    @ResponseBody
    @RequestMapping("/getConstruction")
    public JSONObject getConstruction(String line) {
        Map<String, Object> paramsMap = new HashMap<>();
        if (StringUtils.isEmpty(line)) {
            line = "all";
        }
        paramsMap.put("arg0", line);
//        Long startTime = DateUtil.getStringDateFromDate(DateUtil.getBeforeDayStartDateTime(new Date()));
//        Long endTime = DateUtil.getStringDateFromDate(DateUtil.getDateEndDateTime(new Date()));
        List<ConstructPlan> alarmPlan = new ArrayList<>();
        try {
            String result = WebServiceUtil.push(JxfMonitor.REMOTE_ADDR, "getConstructions", paramsMap);
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (jsonObject.getString("resultCode").equals("00")) {
                String jsonArrayStr1 = jsonObject.get("data").toString();
                alarmPlan = ConstructPushJob.getListFromJsonArray(jsonArrayStr1);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultJson.errorResultJson("");
        }

        return ResultJson.succResultJson(alarmPlan);
    }


    /**
     * 获取线路对应的的数据
     * 线路为空则取全部
     */
    @ResponseBody
    @RequestMapping("/getHistoryConstructions")
    public JSONObject getHistoryConstructions(String line, int pageNum) {
        Map<String, Object> paramsMap = new LinkedHashMap<>();
        if (StringUtils.isNotEmpty(line)) {
            line = "all";
        }
        paramsMap.put("arg0", line);
        paramsMap.put("arg1", pageNum);
        try {
            String result = WebServiceUtil.push(JxfMonitor.REMOTE_ADDR, "getHistoryConstructions", paramsMap);
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (jsonObject.getString("resultCode").equals("00")) {
//            String jsonArrayStr1 = jsonObject.get("data").toString();
//            List<ConstructPlan> constructPlanList = ConstructPushJob.getListFromJsonArray(jsonArrayStr1);
                return jsonObject;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return ResultJson.errorResultJson("");
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

    @ResponseBody
    @RequestMapping("/send")
    public JSONObject sengMessageTest() {
        String constructPushJob = JxfMonitor.casServer("all");
        JSONObject jsonObject = JSONObject.parseObject(constructPushJob);
        List<ConstructPlan> alarmPlan = new ArrayList<>();
        if (jsonObject.getString("resultCode").equals("00")) {
            String jsonArrayStr1 = jsonObject.get("data").toString();
            System.out.println("重大施工OA内容测试:" + jsonArrayStr1);
            List<ConstructPlan> constructPlanList = ConstructPushJob.getListFromJsonArray(jsonArrayStr1);
            //解析
            for (ConstructPlan constructPlan : constructPlanList) {
                alarmPlan.add(constructPlan);
            }
        }
        TicketService ticketService = new TicketService();
        ticketService.refeshTicket();
        TicketVO ticketVO = (TicketVO) SendMsgHelper.localMap.get(Constant.TICKET_KEY);
        for (int i=0;i<alarmPlan.size();i++) {
            if(i==0) {
                ConstructPlan constructPlan = alarmPlan.get(i);
                System.out.println("当前的sonstructPlan为:" + constructPlan.toString());
                ConstructPushJob cjob = new ConstructPushJob();
                Map<String, Object> metroOAMap = cjob.buildSendMessageMap(constructPlan);
                ThreadSendMsg2 threadSendMsg = new ThreadSendMsg2(ticketVO, "重大施工提醒计划", metroOAMap);
                threadSendMsg.run();
            }
        }
        return ResultJson.succResultJson(alarmPlan);
    }

}
