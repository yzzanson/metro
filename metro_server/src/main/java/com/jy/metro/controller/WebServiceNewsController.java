package com.jy.metro.controller;

import com.alibaba.fastjson.JSONObject;
import com.jy.metro.util.DateUtil;
import com.jy.metro.util.WebServiceUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * WebServiceNewsController
 *
 * @author shisan
 * @create 2018-02-07 下午1:53
 **/
@Controller
@RequestMapping("/news")
public class WebServiceNewsController {

    /**
     * 获取新闻信息
     *
     * @author shisan
     * @date 2018/2/7 下午1:59
     */
    @ResponseBody
    @RequestMapping("/getNewsInfo")
    public JSONObject getNewsInfo(Date startDate, Date endDate) {
        JSONObject jsonData = new JSONObject();
        Date currentDate = new Date();
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("user", "xxjsxt");
        paramsMap.put("password", "BCE486DD650A4fb388403AC15250541B");
        paramsMap.put("dataType", "xxjsPicNews");
        paramsMap.put("startDate", startDate == null ? DateUtil.getDateStartDateTime(currentDate) : startDate);
        paramsMap.put("endDate", endDate == null ? DateUtil.getDateEndDateTime(currentDate) : endDate);

        String resultStr = WebServiceUtil.pushMethod("http://10.2.129.65:8018/NewsWebService.asmx", "GetNewsInfo", paramsMap);
        System.out.println(resultStr + "===============");

        jsonData.put("result", 1);
        jsonData.put("data", null);
        jsonData.put("msg", "success");
        return jsonData;
    }

    @ResponseBody
    @RequestMapping("/getConstruction")
    public JSONObject getConstruction() {
        JSONObject jsonData = new JSONObject();

        WebServiceUtil.pushMethod(REMOTE_ADDR, "getConstructions", "all");

        jsonData.put("result", 1);
        jsonData.put("data", null);
        jsonData.put("msg", "success");
        return jsonData;
    }
}
