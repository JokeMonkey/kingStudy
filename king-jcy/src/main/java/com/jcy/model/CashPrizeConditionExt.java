package com.jcy.model;

import java.util.List;

public class CashPrizeConditionExt {
    //指定卡兑换工作
    private CommonCars fixedCards;
    //单租随机卡片兑换规则
    private RandomCards randomCars;
    //多组随机卡兑换规则
    private List<RandomCards> groupRandomCards;
    //万能卡信息
    private CommonCars superCards;
    
    public CommonCars getFixedCards() {
        return fixedCards;
    }
    public void setFixedCards(CommonCars fixedCards) {
        this.fixedCards = fixedCards;
    }
    public RandomCards getRandomCars() {
        return randomCars;
    }
    public void setRandomCars(RandomCards randomCars) {
        this.randomCars = randomCars;
    }
    public CommonCars getSuperCards() {
        return superCards;
    }
    public void setSuperCards(CommonCars superCards) {
        this.superCards = superCards;
    }
    public List<RandomCards> getGroupRandomCards() {
        return groupRandomCards;
    }
    public void setGroupRandomCards(List<RandomCards> groupRandomCards) {
        this.groupRandomCards = groupRandomCards;
    }
}
