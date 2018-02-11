package com.jy.metro.controller;

import com.alibaba.fastjson.JSONObject;
import com.jy.metro.bean.NewsEntity;
import com.jy.metro.util.DateUtil;
import com.jy.metro.util.ResultJson;
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
        Date currentDate = new Date();
        Map<String, Object> paramsMap = new LinkedHashMap<>();
        paramsMap.put("user", "xxjsxt");
        paramsMap.put("password", "BCE486DD650A4fb388403AC15250541B");
        paramsMap.put("dataType", "xxjsPicNews");
        paramsMap.put("startDate", startDate == null ?DateUtil.getDisplayYMDHMS(currentDate)  :startDate  );
        paramsMap.put("endDate", endDate == null ?DateUtil.getDisplayYMDHMS(currentDate) :endDate );

        List<NewsEntity> newsEntityList = null;
        try {
            String resultStr = WebServiceUtil.push("http://10.2.129.65:8018/NewsWebService.asmx", "GetNewsInfo",paramsMap );
            if(!StringUtils.isEmpty(resultStr)){
                newsEntityList = XmlParseUtil.readStringXml(resultStr);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultJson.errorResultJson("");
        }

        return ResultJson.succResultJson(newsEntityList);
    }

    @ResponseBody
    @RequestMapping("/getDataInfoList")
    public JSONObject getDataInfoList(String pagenum, String pagesize) {
        Map<String, Object> paramsMap = new LinkedHashMap<>();
        paramsMap.put("user", "xxjsxt");
        paramsMap.put("password", "BCE486DD650A4fb388403AC15250541B");
        paramsMap.put("dataType", "xxjsPicNews");
        paramsMap.put("pagenum", pagenum);
        paramsMap.put("pagesize", pagesize);

        List<NewsEntity> newsEntityList = null;
        try {
            String resultStr = WebServiceUtil.push("http://10.2.129.65:8018/NewsWebService.asmx", "getDataInfoList",paramsMap );
            if(!StringUtils.isEmpty(resultStr)){
                newsEntityList = XmlParseUtil.readStringXml(resultStr);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultJson.errorResultJson("");
        }

        return ResultJson.succResultJson(newsEntityList);
    }

    @ResponseBody
    @RequestMapping("/getDataInfoById")
    public JSONObject getDataInfoById(String id){
        Map<String, Object> paramsMap = new LinkedHashMap<>();
        paramsMap.put("user", "xxjsxt");
        paramsMap.put("password", "BCE486DD650A4fb388403AC15250541B");
        paramsMap.put("dataType", "xxjsPicNews");
        paramsMap.put("id", id);

        List<NewsEntity> newsEntityList = null;
        try {
            String resultStr = WebServiceUtil.push("http://10.2.129.65:8018/NewsWebService.asmx", "getDataInfoById",paramsMap );
            if(!StringUtils.isEmpty(resultStr)){
                newsEntityList = XmlParseUtil.readStringXml(resultStr);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultJson.errorResultJson("");
        }

        return ResultJson.succResultJson(newsEntityList);
    }

    @ResponseBody
    @RequestMapping("/getInfo")
    public JSONObject getInfo(){
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("arg0",  "all");
        String s = null;
        try {
             s =  WebServiceUtil.push("http://10.201.1.1/services/in?wsdl", "getConstructions", paramsMap);
        }catch (Exception e){
            e.printStackTrace();
            return ResultJson.errorResultJson("");
        }

        return ResultJson.succResultJson(s);
    }
}
