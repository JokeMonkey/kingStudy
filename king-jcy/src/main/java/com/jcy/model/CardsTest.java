package com.jcy.model;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

public class CardsTest {
    public static void main(String[] args) {
        CashPrizeCondition cashPrizeCondition = new CashPrizeCondition();
        cashPrizeCondition.setId(1L);
        cashPrizeCondition.setMemo("集齐卡片兑换妹子");
        cashPrizeCondition.setCashPrizeRuleId(1L);
        cashPrizeCondition.setCardGroupIds("1,2,3,4,5");
        
        //指定卡片
        List<Long> fixedCars = new ArrayList<>();
        fixedCars.add(7L);
        fixedCars.add(8L);
        fixedCars.add(9L);
        
        //单组随机
        RandomCards randomCars = new RandomCards();
        randomCars.setNum(3);
        List<Long> cards = new ArrayList<>();
        cards.add(1L);
        cards.add(2L);
        cards.add(3L);
        cards.add(4L);
        cards.add(5L);
        cards.add(6L);
        randomCars.setCards(cards);
        
        //多组随机
        List<RandomCards> groupRandomCards = new ArrayList<>();
        RandomCards randomCars1 = new RandomCards();
        randomCars1.setNum(1);
        List<Long> cards1 = new ArrayList<>();
        cards1.add(11L);
        cards1.add(12L);
        cards1.add(13L);
        cards1.add(14L);
        cards1.add(15L);
        cards1.add(16L);
        randomCars1.setCards(cards1);
        
        RandomCards randomCars2 = new RandomCards();
        randomCars2.setNum(2);
        List<Long> cards2 = new ArrayList<>();
        cards2.add(21L);
        cards2.add(22L);
        cards2.add(23L);
        cards2.add(24L);
        cards2.add(25L);
        cards2.add(26L);
        randomCars2.setCards(cards2);
        
        RandomCards randomCars3 = new RandomCards();
        randomCars3.setNum(3);
        List<Long> cards3 = new ArrayList<>();
        cards3.add(31L);
        cards3.add(32L);
        cards3.add(33L);
        cards3.add(34L);
        cards3.add(35L);
        cards3.add(36L);
        randomCars3.setCards(cards3);
        groupRandomCards.add(randomCars1);
        groupRandomCards.add(randomCars2);
        groupRandomCards.add(randomCars3);
        
        System.out.println(JSON.toJSONString(groupRandomCards));
        
        
        CashPrizeConditionExt ext = new CashPrizeConditionExt();
        
        
        System.out.println(JSON.toJSONString(ext));
    }
}
