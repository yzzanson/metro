package com.jy.metro.bean.vo;

import java.util.Date;

/**
 * 钉钉消息
 * Created by Saber on 2016/8/16.
 */
public class TicketVO {

    /**
     * tickets表主键
     */
    private Integer id;

    /**
     * jsTicket
     */
    private String ticket;
    /**
     * accessToken
     */
    private String accessToken;
    /**
     * @Link https://open-doc.dingtalk.com/docs/doc.htm?spm=a219a.7629140.0.0.5Jdo84&treeId=172&articleId=104973&docType=1
     *
     * 收银在组织架构的钉Id  （消息发送目标人) [  touser 员工id列表（消息接收者，多个接收者用|分隔）]
     */
    private String dingId;
    /**
     * 部门id                （消息发送给整个部门）[ toparty 部门id列表，多个接收者用|分隔。touser或者toparty 二者有一个必填]
     */
    private String departmentId;
    /**
     * 收银部门id
     */
    private String cashierDeptId;
    /**
     * 组织架构Id           （消息发送组织架构）
     */
    private String corpId;

    private String secrect;
    /**
     * 微应用Id             （消息发送的微应用Id）[ agentid 企业应用id，这个值代表以哪个应用的名义发送消息]
     */
    private String agentId;

    /**
     * 活动管理部门
     */
    private String lotteryDeptId;

    /**
     * 数据最后刷新时间 用于判断accessToken是否过期
     */
    private Date updatedAt;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getDingId() {
        return dingId;
    }

    public void setDingId(String dingId) {
        this.dingId = dingId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getCashierDeptId() {
        return cashierDeptId;
    }

    public void setCashierDeptId(String cashierDeptId) {
        this.cashierDeptId = cashierDeptId;
    }

    public String getLotteryDeptId() {
        return lotteryDeptId;
    }

    public void setLotteryDeptId(String lotteryDeptId) {
        this.lotteryDeptId = lotteryDeptId;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getSecrect() {
        return secrect;
    }

    public void setSecrect(String secrect) {
        this.secrect = secrect;
    }

    @Override
    public String toString() {
        return "TicketVO{" +
                "id=" + id +
                ", ticket='" + ticket + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", dingId='" + dingId + '\'' +
                ", departmentId='" + departmentId + '\'' +
                ", cashierDeptId='" + cashierDeptId + '\'' +
                ", corpId='" + corpId + '\'' +
                ", agentId='" + agentId + '\'' +
                ", lotteryDeptId='" + lotteryDeptId + '\'' +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
