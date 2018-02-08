package com.jy.metro.service;

//import org.apache.axis.client.Call;
//import org.apache.axis.client.Service;

//import javax.xml.soap.SOAPException;

import com.jy.metro.util.WebServiceUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by anson on 2018/2/6.20:00
 */
public class JxfMonitor {

    public static String REMOTE_ADDR = "http://10.201.1.1/services/in?wsdl";

    public static String casServer(String lineNumber) {
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("arg0", lineNumber);
        return WebServiceUtil.push(REMOTE_ADDR, "getConstructions", paramsMap);
    }


    public static void main(String[] args) {
        System.out.println(casServer("all"));
    }

}
