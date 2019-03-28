package com.jcy.model;

public class CashPrizeCondition {
    //兑换条件编码
    private Long id;
    //兑换条件描述
    private String memo;
    //兑换规则编码
    private Long cashPrizeRuleId;
    //卡组id集合
    private String cardGroupIds;
    //扩展字段
    private String ext;
    
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
    public Long getCashPrizeRuleId() {
        return cashPrizeRuleId;
    }
    public void setCashPrizeRuleId(Long cashPrizeRuleId) {
        this.cashPrizeRuleId = cashPrizeRuleId;
    }
    public String getCardGroupIds() {
        return cardGroupIds;
    }
    public void setCardGroupIds(String cardGroupIds) {
        this.cardGroupIds = cardGroupIds;
    }
    public String getExt() {
        return ext;
    }
    public void setExt(String ext) {
        this.ext = ext;
    }
}
