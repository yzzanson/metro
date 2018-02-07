package com.jy.metro.util;

import org.apache.soap.Constants;
import org.apache.soap.Fault;
import org.apache.soap.rpc.Call;
import org.apache.soap.rpc.Parameter;
import org.apache.soap.rpc.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

/**
 * WebServiceUtil
 *
 * @author shisan
 * @create 2018-02-07 下午1:44
 **/
public class WebServiceUtil {

    private static Logger logger = LoggerFactory.getLogger(WebServiceUtil.class);

    public static String pushMethod(String webServiceUrl, String method, String lineNumber) {
        //标识Web Service的具体路径
        URL url = null;
        try {
            url = new URL(webServiceUrl);
        } catch (MalformedURLException mue) {
            logger.error("创建url失败,", mue);
            return mue.getMessage();
        }
        // This is the main SOAP object
        Call soapCall = new Call();
        // Use SOAP encoding
        soapCall.setEncodingStyleURI(Constants.NS_URI_SOAP_ENC);
        // This is the remote object we're asking for the price
        soapCall.setTargetObjectURI("urn:xmethods-caSynrochnized");
        // This is the name of the method on the above object
        soapCall.setMethodName(method);
        // We need to send the ISBN number as an input parameter to the method
        if (!StringUtils.isEmpty(lineNumber)) {
            Vector soapParams = new Vector();
            // name, type, value, encoding style
            Parameter isbnParam = new Parameter("arg0", String.class, lineNumber, null);
            soapParams.addElement(isbnParam);
            // soapParams.addElement(isbnParam1);
            soapCall.setParams(soapParams);
        }

        try {
            // Invoke the remote method on the object
            Response soapResponse = soapCall.invoke(url, "");
            // Check to see if there is an error, return "N/A"
            if (soapResponse.generatedFault()) {
                Fault fault = soapResponse.getFault();
                return fault.getFaultString();
            } else {
                // read result
                Parameter soapResult = soapResponse.getReturnValue();
                // get a string from the result
                return soapResult.getValue().toString();
            }
        } catch (Exception se) {
            logger.error(webServiceUrl + "发送失败,", se);
            return se.getMessage();
        }
    }
}
