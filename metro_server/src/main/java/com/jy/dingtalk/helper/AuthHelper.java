package com.jy.dingtalk.helper;

import com.alibaba.fastjson.JSONObject;
import com.jy.dingtalk.constant.Contanst;
import com.jy.dingtalk.entity.OaMsgEntity;
import com.jy.dingtalk.exception.OApiException;
import com.jy.dingtalk.exception.OApiResultException;
import com.jy.dingtalk.message.SimpleOAMessage;
import com.jy.dingtalk.util.JSONUtil;
import com.jy.metro.util.HttpUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;

/**
 * Created by houminglong on 16/8/11.
 */
public class AuthHelper {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AuthHelper.class.getCanonicalName());
    public static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 根据corpid ,secret(从数据中获取)  获取 access_token值
     *
     * @param corpId
     * @param secret
     * @return access_token
     */
    public static String getAccessToken(String corpId, String secret) throws Exception {
        String accessToken = null;
        JSONObject object = new JSONObject();
        object.put(Contanst.CORP_ID, corpId);
        object.put(Contanst.CORP_SECRET, secret);
        //根据json参数获取最终需要请求的url
        String url = JSONUtil.appendJsonParamsToUrl(Contanst.GET_ACCESS_TOKEN_URL, object);
        JSONObject result = HttpUtil.get(url);
        accessToken = (String) result.get("access_token");
        return accessToken;
    }

    /**
     * add by xiaobai
     * */
    public static OaMsgEntity sendOAReturn(SimpleOAMessage oaMessage) throws OApiException {

        String toUser = oaMessage.getToUser();// 多个用|分隔
        String toParty = oaMessage.getToParty();// 多个用|分隔
        String accessToken = oaMessage.getAccessToken();
        String agentId = oaMessage.getAgentId();
        String corpId = oaMessage.getCorpId();


        if(StringUtils.isBlank(toUser) && StringUtils.isBlank(toParty)){
            logger.debug("发送oa消息,人与部门都没填");
        }

        if((StringUtils.isBlank(accessToken) || StringUtils.isBlank(agentId)) && StringUtils.isBlank(corpId)){
            logger.debug("发送oa消息,无法定位到具体企业");
        }
        else if(StringUtils.isNotBlank(corpId)){
            oaMessage.setAccessToken(accessToken);
            oaMessage.setAgentId(agentId);

        }

        String url = Contanst.OAPI_HOST + "/message/send?" + "access_token=" + accessToken;

        JSONObject response = HttpUtil.httpPost(url, oaMessage.toJSONObject());


        if (response.containsKey("invaliduser") || response.containsKey("invalidparty")) {
            OaMsgEntity msg = new OaMsgEntity();
            msg.setErrCode(response.getInteger("errcode"));
            msg.setErrMsg(response.getString("errmsg"));
            msg.setInvalidParty(response.getString("invalidparty"));
            msg.setInvalidUser(response.getString("invaliduser"));
            msg.setMessageId(response.getString("messageId"));
            return msg;
        }
        else {
            throw new OApiResultException("invaliduser or invalidparty");
        }
    }


    /**
     * @param accessToken
     * @return ticket
     * @throws Exception
     */
    public static String getJsapiTicket(String accessToken) throws Exception {
        String jsTicket = "";
        JSONObject object = new JSONObject();
        object.put(Contanst.ACCESS_TOKEN, accessToken);
        //根据json参数获取最终需要请求的url
        String url = JSONUtil.appendJsonParamsToUrl(Contanst.GET_JSAPI_TICKET_URL, object);
        JSONObject result = HttpUtil.get(url);
        jsTicket = result.get("ticket").toString();
        return jsTicket;
    }

}
