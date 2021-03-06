package com.jy.metro.job;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jy.dingtalk.SendMsgHelper;
import com.jy.dingtalk.threadMsg.ThreadSendMsg;
import com.jy.metro.bean.ConstructPlan;
import com.jy.metro.bean.vo.TicketVO;
import com.jy.metro.common.Constant;
import com.jy.metro.service.JxfMonitor;
import com.jy.metro.service.TicketService;
import com.jy.metro.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;


/**
 * 刷新列表判断当前激活码是否过期
 * Created by anson on 18/1/30.
 */
@Component
public class ConstructPushJob {

    private static final Logger logger = LoggerFactory.getLogger(ConstructPushJob.class);

    private static final String ALL_LINE = "all";

    private static final String MESSAGE_TITLE = "重大施工提醒计划";

    /**
     * 每天9点执行一次
     * 获取今天和昨天的所有所有
     * */
    @Scheduled(cron="0 0 9 * * *")
//    @Scheduled(cron="0 0/10 * * * ?")
    public void remindConstruct() {
        logger.info("施工推送开始...");

        TicketService ticketService = new TicketService();
        ticketService.refeshTicket();

        Long startTime = DateUtil.getStringDateFromDate(new Date());
        Long endTime = DateUtil.getStringDateFromDate(new Date());
        String constructPushJob = JxfMonitor.casServer(ALL_LINE);
        JSONObject jsonObject = JSONObject.parseObject(constructPushJob);
        List<ConstructPlan> alarmPlan = new ArrayList<>();
        if(jsonObject.getString("resultCode").equals("00")){
            String jsonArrayStr1 = jsonObject.get("data").toString();
            List<ConstructPlan> constructPlanList = getListFromJsonArray(jsonArrayStr1);
            //解析
            for (ConstructPlan constructPlan:constructPlanList){
                if(constructPlan.getPlanStartTime()>=startTime && constructPlan.getPlanStartTime()<=endTime){
                    alarmPlan.add(constructPlan);
                }
            }
        }
        TicketVO ticketVO = (TicketVO) SendMsgHelper.localMap.get(Constant.TICKET_KEY);
        for(ConstructPlan constructPlan:alarmPlan){
            Map<String,Object> metroOAMap = buildSendMessageMap(constructPlan);
            ThreadSendMsg threadSendMsg = new ThreadSendMsg(ticketVO,MESSAGE_TITLE,metroOAMap);
            threadSendMsg.run();
        }
        //查询需要重新统计数据的网吧Id列表
        logger.info("施工推送结束...");
    }

    public Map<String,Object> buildSendMessageMap(ConstructPlan constructPlan){
        Map<String,Object> oaSendMap = new LinkedHashMap<>();
        oaSendMap.put("线路名称:", constructPlan.getLineName());
        oaSendMap.put("施工时间:", DateUtil.getDateFromLong(constructPlan.getPlanStartTime())+" - "+constructPlan.getPlanEndTime());
        oaSendMap.put("申报单位:", constructPlan.getDeclareUnitName());
        oaSendMap.put("施工单位:", constructPlan.getConstructionUnitName());
        oaSendMap.put("施工内容:", constructPlan.getWorkContent());
        oaSendMap.put("", "该施工计划已批复");
        return oaSendMap;
    }

    public static List<ConstructPlan> getListFromJsonArray(String jsonArrayStr){
        List<ConstructPlan> list = new ArrayList<>();
        JSONArray jsonArray = JSONArray.parseArray(jsonArrayStr);
        if (jsonArray==null || jsonArray.isEmpty()) {
            return  null;
        }
        for (Object object : jsonArray) {
            JSONObject jsonObject = (JSONObject) object;
            ConstructPlan t = JSONObject.toJavaObject(jsonObject, ConstructPlan.class);
            list.add(t);
        }
        return list;
    }

    public static void main(String[] args) {
        String jsonArrayStr = "{\"resultCode\":\"00\",\"data\":[{\"lineId\":\"02\",\"lineName\":\"二号线\",\"planStartTime\":\"201802090100\",\"planEndTime\":\"201802090330\",\"declareUnitName\":\"供电分公司\",\"constructionUnitName\":\"触网检修二部\",\"workContent\":\"二号线一期接触网设备更新、改造（二级重大施工）\",\"publishTime\":\"20180202113133\"},{\"lineId\":\"04\",\"lineName\":\"四号线\",\"planStartTime\":\"201802070010\",\"planEndTime\":\"201802070330\",\"declareUnitName\":\"通号分公司\",\"constructionUnitName\":\"通号\",\"workContent\":\"信号中央软件升级（临平路ATS系统倒接开关接入，含机房）（二级重大）\",\"publishTime\":\"20180202100731\"},{\"lineId\":\"05\",\"lineName\":\"五号线\",\"planStartTime\":\"201802062330\",\"planEndTime\":\"201802070330\",\"declareUnitName\":\"通号分公司\",\"constructionUnitName\":\"通号\",\"workContent\":\"5号线既有线信号系统改造施工项目（道岔倒接）（含信号机房）（二级重大）\",\"publishTime\":\"20180206202756\"},{\"lineId\":\"05\",\"lineName\":\"五号线\",\"planStartTime\":\"201802062355\",\"planEndTime\":\"201802070330\",\"declareUnitName\":\"供电分公司\",\"constructionUnitName\":\"第五运修部\",\"workContent\":\"剑川路“4改6”接触网改造施工（二级重大施工）\",\"publishTime\":\"20180206202712\"},{\"lineId\":\"05\",\"lineName\":\"五号线\",\"planStartTime\":\"201802072340\",\"planEndTime\":\"201802080345\",\"declareUnitName\":\"通号分公司\",\"constructionUnitName\":\"通号\",\"workContent\":\"信号系统改造动车测试（0516#电客1列，含岔区/机房）二级重大\",\"publishTime\":\"20180202112825\"},{\"lineId\":\"05\",\"lineName\":\"五号线\",\"planStartTime\":\"201802082355\",\"planEndTime\":\"201802090330\",\"declareUnitName\":\"供电分公司\",\"constructionUnitName\":\"第五运修部\",\"workContent\":\"剑川路“4改6”接触网改造施工（二级重大施工）\",\"publishTime\":\"20180202152832\"},{\"lineId\":\"05\",\"lineName\":\"五号线\",\"planStartTime\":\"201802092330\",\"planEndTime\":\"201802100330\",\"declareUnitName\":\"通号分公司\",\"constructionUnitName\":\"通号\",\"workContent\":\"5号线既有线信号系统改造施工项目（道岔倒接）（含信号机房）（二级重大）\",\"publishTime\":\"20180202112902\"},{\"lineId\":\"05\",\"lineName\":\"五号线\",\"planStartTime\":\"201802092355\",\"planEndTime\":\"201802100330\",\"declareUnitName\":\"供电分公司\",\"constructionUnitName\":\"第五运修部\",\"workContent\":\"剑川路“4改6”接触网改造施工（二级重大施工）\",\"publishTime\":\"20180202152832\"},{\"lineId\":\"06\",\"lineName\":\"六号线\",\"planStartTime\":\"201802070020\",\"planEndTime\":\"201802070330\",\"declareUnitName\":\"车辆分公司\",\"constructionUnitName\":\"车辆公司维修五部\",\"workContent\":\"2列车防撞试验（二级重大）\",\"publishTime\":\"20180202101918\"},{\"lineId\":\"08\",\"lineName\":\"八号线\",\"planStartTime\":\"201802062359\",\"planEndTime\":\"201802070430\",\"declareUnitName\":\"运营四公司\",\"constructionUnitName\":\"上海吉宁消防工程有限公司\",\"workContent\":\"8号线一期、二期FAS系统全线下载程序\",\"publishTime\":\"20180205102329\"},{\"lineId\":\"12\",\"lineName\":\"十二号线\",\"planStartTime\":\"201802062359\",\"planEndTime\":\"201802070430\",\"declareUnitName\":\"运营四公司\",\"constructionUnitName\":\"上海杰东工程有限公司\",\"workContent\":\"12号线FAS系统全线下载程序\",\"publishTime\":\"20180205102349\"}],\"msg\":\"数据传输成功！\"}";
        JSONObject jsonObject = JSONObject.parseObject(jsonArrayStr);
        String jsonArrayStr1 = jsonObject.get("data").toString();
        List<ConstructPlan> constructPlanList = getListFromJsonArray(jsonArrayStr1);
        for (int i = 0; i <constructPlanList.size() ; i++) {
            ConstructPlan constructPlan =  constructPlanList.get(i);
            System.out.println(constructPlan.toString());
        }
    }
}
