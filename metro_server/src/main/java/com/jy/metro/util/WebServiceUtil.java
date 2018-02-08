package com.jy.metro.util;

//import org.apache.axis.client.Call;

import org.apache.axis.client.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.encoding.XMLType;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * WebServiceUtil
 *
 * @author shisan
 * @create 2018-02-07 下午1:44
 **/
public class WebServiceUtil {

    private static Logger logger = LoggerFactory.getLogger(WebServiceUtil.class);

    public static String push(String webServiceUrl, String method, Map<String, String> paramsMap){
        String qName ="http://tempuri.org/";
        String result = null;
        try {
            //直接引用远程的wsdl文件
            //以下都是套路
            Service service = new Service();
            org.apache.axis.client.Call call = (org.apache.axis.client.Call) service.createCall();
            call.setTargetEndpointAddress(new URL(webServiceUrl));
            call.setUseSOAPAction(true);
            call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);//设置返回类型
            call.setOperationName(new QName(qName,method));//WSDL里面描述的接口名称
             /*    JAVA调用NET 报服务器未能识别 HTTP 头 SOAPAction 的值,遇到这种问题时,是因为没有设SOAPAction
              的值,加上这行代码就可以call.setSOAPActionURI("http://tempuri.org/getLocalJson"),注意后面的Add方法哦,是方法名,一定要带哦*/
            String path = qName + method;
            call.setSOAPActionURI(path);
            List<String> paramList = new ArrayList<>();
            for (String param : paramsMap.keySet()) {
                call.addParameter(new QName(qName,param), XMLType.XSD_STRING, ParameterMode.IN);
                paramList.add((String)paramsMap.get(param));
            }

            result = (String)call.invoke(paramList.toArray());
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            logger.error(webServiceUrl + "RemoteException,", e);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            logger.error(webServiceUrl + "发送失败,", e);
        }
        return result;
    }

}
