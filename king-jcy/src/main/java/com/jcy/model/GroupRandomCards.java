package com.jcy.model;

import java.util.List;

public class GroupRandomCards {
    //多卡组随机兑换
    private List<RandomCards> groupRandomCards;
    
    public List<RandomCards> getGroupRandomCards() {
        return groupRandomCards;
    }
    public void setGroupRandomCards(List<RandomCards> groupRandomCards) {
        this.groupRandomCards = groupRandomCards;
    }
}
