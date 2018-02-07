package com.jy.dingtalk.threadMsg;

import com.alibaba.fastjson.JSONObject;
import com.jy.dingtalk.SendMsgHelper;
import com.jy.metro.bean.vo.TicketVO;
import com.jy.metro.util.DateUtil;
import org.apache.axis.utils.StringUtils;
import org.slf4j.Logger;

import java.util.Map;

/**
 * 异步发送消息
 * Created by anson on 2018/2/7.
 */
public class ThreadSendMsg implements Runnable {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(ThreadSendMsg.class.getCanonicalName());

    private String title;
    private Map<String, Object> map;
    private TicketVO config;

    public ThreadSendMsg(TicketVO config, String title, Map<String, Object> map) {
        this.config = config;
        this.title = title;
        this.map = map;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p/>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        try {
            //获取 ticketDao
            if (config == null || StringUtils.isEmpty(config.getDepartmentId())) {
                String errMsg = "错误：收银发送钉消息时获取ticket配置为null\n或部门为空\n发送时间：" + DateUtil.getCurrentTime();
                logger.debug(errMsg);
            } else {
                //发送消息
                //                JSONObject resultMsg = SendMsgHelper.sendTextMsgToCashier(wangbaId, textMsg);
                JSONObject resultMsg = SendMsgHelper.sendOAMsgToDept(title,config, map);
                int i = 0;
                StringBuffer oa_msg_content = new StringBuffer();
                if (map != null && map.size() > 0) {
                    for (String key : map.keySet()) {
                        if (map.get(key) != null) {
                            oa_msg_content.append(key).append(map.get(key));
                            if (i < map.size()) {
                                oa_msg_content.append("\n");
                            }
                        }
                    }
                }
                // 发送成功 记入oa_msg表
                if ("0".equals(resultMsg.getString("result"))) {
                    logger.info("发送OA消息成功");
                }
                logger.debug("给收银发送钉消息结果：" + resultMsg.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }


}
