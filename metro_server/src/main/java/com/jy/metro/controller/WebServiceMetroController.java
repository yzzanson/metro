package com.jy.metro.controller;

import com.alibaba.fastjson.JSONObject;
import com.jy.dingtalk.SendMsgHelper;
import com.jy.metro.bean.vo.TicketVO;
import com.jy.metro.common.Constant;
import com.jy.metro.service.JxfMonitor;
import com.jy.metro.service.TicketService;
import com.jy.metro.util.ResultJson;
import com.jy.metro.util.WebServiceUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

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
        WebServiceUtil.pushMethod(JxfMonitor.REMOTE_ADDR, "getConstructions", paramsMap);

        jsonData.put("result", 1);
        jsonData.put("data", null);
        jsonData.put("msg", "success");
        return jsonData;
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
