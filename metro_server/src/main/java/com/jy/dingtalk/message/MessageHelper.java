package com.jy.dingtalk.message;


import com.alibaba.fastjson.JSONObject;
import com.jy.dingtalk.constant.Contanst;
import com.jy.dingtalk.entity.OaMsgEntity;
import com.jy.dingtalk.exception.OApiException;
import com.jy.dingtalk.exception.OApiResultException;
import com.jy.metro.util.HttpUtil;

public class MessageHelper {

	public static class Receipt {
		String invaliduser;
		String invalidparty;
	}

	/**
	 *
	 * @param accessToken
	 * @param delivery
	 * @return
	 * @throws OApiException
	 */
	public static OaMsgEntity send(String accessToken, LightAppMessageDelivery delivery)
			throws OApiException {
		String url = Contanst.OAPI_HOST + "/message/send?" + "access_token=" + accessToken;

		JSONObject response = HttpUtil.httpPost(url, delivery.toJsonObject());
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

	public static void main(String[] args) {
		String accessToken = "2290a9088d0a38489ca105bbe3fa02ed";
		String messageId = "b24fee6132843a4692190ac811dd90d1";
		//accessToken="11c48a9c625f38e794664c191b4527c8";
		messageId="78732610fe02373193feb9e692dbc3e2";
		messageId="b7fd2a47b7f236249f4850ca61df6a13";
	}
}
