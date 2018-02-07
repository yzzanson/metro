package com.jy.metro.bean;

/**
 * Created by anson on 18/2/6.
 */
public class ConstructPlan {

    private Long publishTime;

    private Long planStartTime;

    private String workContent;

    private String lineId;

    private String lineName;

    private Long planEndTime;

    private String declareUnitName;

    private String constructionUnitName;


    public Long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Long publishTime) {
        this.publishTime = publishTime;
    }

    public Long getPlanStartTime() {
        return planStartTime;
    }

    public void setPlanStartTime(Long planStartTime) {
        this.planStartTime = planStartTime;
    }

    public String getWorkContent() {
        return workContent;
    }

    public void setWorkContent(String workContent) {
        this.workContent = workContent;
    }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public Long getPlanEndTime() {
        return planEndTime;
    }

    public void setPlanEndTime(Long planEndTime) {
        this.planEndTime = planEndTime;
    }

    public String getDeclareUnitName() {
        return declareUnitName;
    }

    public void setDeclareUnitName(String declareUnitName) {
        this.declareUnitName = declareUnitName;
    }

    public String getConstructionUnitName() {
        return constructionUnitName;
    }

    public void setConstructionUnitName(String constructionUnitName) {
        this.constructionUnitName = constructionUnitName;
    }

    @Override
    public String toString() {
        return "ConstructPlan{" +
                "publishTime=" + publishTime +
                ", planStartTime=" + planStartTime +
                ", workContent='" + workContent + '\'' +
                ", lineId='" + lineId + '\'' +
                ", lineName='" + lineName + '\'' +
                ", planEndTime=" + planEndTime +
                ", declareUnitName='" + declareUnitName + '\'' +
                ", constructionUnitName='" + constructionUnitName + '\'' +
                '}';
    }
}
