package com.jcy.model;

import java.util.List;

public class CommonCars {
    //需要兑奖的卡片id集合
    private List<Long> cards;
    //卡组id集合
    private List<Long> groupCards;
    //卡片数量
    private Integer num;
    
    public List<Long> getGroupCards() {
        return groupCards;
    }
    public void setGroupCards(List<Long> groupCards) {
        this.groupCards = groupCards;
    }
    public List<Long> getCards() {
        return cards;
    }
    public void setCards(List<Long> cards) {
        this.cards = cards;
    }
    public Integer getNum() {
        return num;
    }
    public void setNum(Integer num) {
        this.num = num;
    }
}
