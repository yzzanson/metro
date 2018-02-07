package com.jy.metro.service;

//import org.apache.axis.client.Call;
//import org.apache.axis.client.Service;

//import javax.xml.soap.SOAPException;

import org.apache.soap.Constants;
import org.apache.soap.Fault;
import org.apache.soap.rpc.Call;
import org.apache.soap.rpc.Parameter;
import org.apache.soap.rpc.Response;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

/**
 * Created by anson on 2018/2/6.20:00
 */
public class JxfMonitor {

    private static String REMOTE_ADDR = "http://10.201.1.1/services/in?wsdl";

    public static String casServer(String lineNumber){
        String endpoint = REMOTE_ADDR;
        URL url = null;
        try {
            url = new URL(
                    endpoint);
        } catch (MalformedURLException mue) {
            return mue.getMessage();
        }
        // This is the main SOAP object
        Call soapCall = new Call();
        // Use SOAP encoding
        soapCall.setEncodingStyleURI(Constants.NS_URI_SOAP_ENC);
        // This is the remote object we're asking for the price
        soapCall.setTargetObjectURI("urn:xmethods-caSynrochnized");
        // This is the name of the method on the above object
        soapCall.setMethodName("getConstructions");
        // We need to send the ISBN number as an input parameter to the method
        Vector soapParams = new Vector();
        // name, type, value, encoding style
        Parameter isbnParam = new Parameter("arg0",String.class, lineNumber,
                null);
//        Parameter isbnParam1 = new Parameter("arg1",int.class, "7",
//                null);
        soapParams.addElement(isbnParam);
//        soapParams.addElement(isbnParam1);
        soapCall.setParams(soapParams);
        try {
            // Invoke the remote method on the object
            Response soapResponse = soapCall.invoke(url, "");
            // Check to see if there is an error, return "N/A"
            if (soapResponse.generatedFault()) {
                Fault fault = soapResponse.getFault();
                String f = fault.getFaultString();
                return f;
            } else {
                // read result
                Parameter soapResult = soapResponse.getReturnValue();
                // get a string from the result
                return soapResult.getValue().toString();
            }
        } catch (Exception se) {
            return se.getMessage();
        }
    }


    public static void main(String[] args) {
        System.out.println(casServer("all"));
    }

}
