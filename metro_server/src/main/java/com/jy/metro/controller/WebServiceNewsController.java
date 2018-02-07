package com.jy.metro.controller;

import com.alibaba.fastjson.JSONObject;
import com.jy.metro.util.WebServiceUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
     public JSONObject getNewsInfo() {
        JSONObject jsonData = new JSONObject();

        WebServiceUtil.pushMethod("http://10.2.129.65:8018/NewsWebService.asmx", "GetNewsInfo", null);

        jsonData.put("result", 1);
        jsonData.put("data", null);
        jsonData.put("msg", "success");
        return jsonData;
    }

    private static String REMOTE_ADDR = "http://10.201.1.1/services/in?wsdl";


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
