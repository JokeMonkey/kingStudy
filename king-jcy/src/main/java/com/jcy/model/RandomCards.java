package com.jcy.model;

import java.util.List;

public class RandomCards {
    //卡片数量
    private Integer num;
    //卡片id集合
    private List<Long> cards;
    //卡组id的集合
    private List<Long> groupCards;
    
    public Integer getNum() {
        return num;
    }
    public void setNum(Integer num) {
        this.num = num;
    }
    public List<Long> getCards() {
        return cards;
    }
    public void setCards(List<Long> cards) {
        this.cards = cards;
    }
    public List<Long> getGroupCards() {
        return groupCards;
    }
    public void setGroupCards(List<Long> groupCards) {
        this.groupCards = groupCards;
    }
}
