package com.jy.metro.controller;

import com.alibaba.fastjson.JSONObject;
import com.jy.metro.bean.NewsEntity;
import com.jy.metro.util.DateUtil;
import com.jy.metro.util.WebServiceUtil;
import com.jy.metro.util.XmlParseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

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
    public JSONObject getNewsInfo(String startDate, String endDate) {
        JSONObject jsonData = new JSONObject();
        Date currentDate = new Date();
        Map<String, String> paramsMap = new LinkedHashMap<>();
        paramsMap.put("user", "xxjsxt");
        paramsMap.put("password", "BCE486DD650A4fb388403AC15250541B");
        paramsMap.put("dataType", "xxjsPicNews");
        paramsMap.put("startDate", startDate == null ?DateUtil.getDisplayYMDHMS(currentDate)  :startDate  );
        paramsMap.put("endDate", endDate == null ?DateUtil.getDisplayYMDHMS(currentDate) :endDate );

        String resultStr = WebServiceUtil.push("http://10.2.129.65:8018/NewsWebService.asmx", "GetNewsInfo",paramsMap );
        List<NewsEntity> newsEntityList = null;
        if(!StringUtils.isEmpty(resultStr)){
//            JSONObject resutJson = JSONObject.parseObject(resultStr);
            newsEntityList = XmlParseUtil.readStringXml(resultStr);
        }

        jsonData.put("result", 1);
        jsonData.put("data", newsEntityList);
        jsonData.put("msg", "success");
        return jsonData;
    }
    @ResponseBody
    @RequestMapping("/getInfo")
    public JSONObject getInfo(){
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("arg0",  "all");
        String s =  WebServiceUtil.push("http://10.201.1.1/services/in?wsdl", "getConstructions", paramsMap);
        JSONObject jsonData = new JSONObject();
        jsonData.put("result", 1);
        jsonData.put("data", s);
        jsonData.put("msg", "success");
        return jsonData;
    }
}
