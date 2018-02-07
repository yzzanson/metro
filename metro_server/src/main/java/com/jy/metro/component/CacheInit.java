package com.jy.metro.component;

import com.jy.dingtalk.SendMsgHelper;
import com.jy.metro.bean.vo.TicketVO;
import com.jy.metro.common.Constant;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * Created by anson on 18/2/7.
 */
@Component
@Order(1)
public class CacheInit implements CommandLineRunner {

    @Override
    public void run(String... strings) throws Exception {
        TicketVO ticketvoInCache = (TicketVO) SendMsgHelper.localMap.get("ticket");
        Properties properties = PropertiesLoaderUtils.loadAllProperties("ticket.properties");
        String corpid = (String) properties.get("corpid");
        String corpsecrect = (String) properties.get("corpsecrect");
        String agentid = (String) properties.get("agentid");
        String departmentid = (String) properties.get("departmentid");
        TicketVO ticketvo = new TicketVO();
        ticketvo.setCorpId(corpid);
        ticketvo.setSecrect(corpsecrect);
        ticketvo.setAgentId(agentid);
        ticketvo.setDepartmentId(departmentid);
        if(ticketvoInCache!=null && ticketvoInCache.getUpdatedAt()!=null){
            ticketvo.setUpdatedAt(ticketvoInCache.getUpdatedAt());
        }
        SendMsgHelper.localMap.put(Constant.TICKET_KEY,ticketvo);
    }

}
