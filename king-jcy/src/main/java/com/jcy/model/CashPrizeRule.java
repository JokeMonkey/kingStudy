package com.jcy.model;

import java.util.Date;

public class CashPrizeRule {
    //兑换规则编码
    private Long id;
    //兑换规则名称
    private String name;
    //兑换规则描述
    private String memo;
    //奖品池id
    private Long prizePoolId;
    //是否支持重复兑奖
    private Integer repeat;
    //活动编码
    private Long activityId;
    //开始时间
    private Date startTime;
    //结束时间
    private Date endTime;
    
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getMemo() {
        return memo;
    }
    public void setMemo(String memo) {
        this.memo = memo;
    }
    public Long getPrizePoolId() {
        return prizePoolId;
    }
    public void setPrizePoolId(Long prizePoolId) {
        this.prizePoolId = prizePoolId;
    }
    public Integer getRepeat() {
        return repeat;
    }
    public void setRepeat(Integer repeat) {
        this.repeat = repeat;
    }
    public Long getActivityId() {
        return activityId;
    }
    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }
    public Date getStartTime() {
        return startTime;
    }
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    public Date getEndTime() {
        return endTime;
    }
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
