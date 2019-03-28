package com.jcy.controller;



import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class CreateSQL {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        String title = "##创建表common_prize_record  add by wangyaoyao    2019/02/20";
        
        
        String pre = "CREATE TABLE `common_prize_record";
        String aft = "` (`id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '奖品记录编码',"
                +"`syscode` varchar(16) NOT NULL COMMENT '系统标识',"
                +"`activity_id` varchar(32) NOT NULL COMMENT '活动标识',"
                +"`order_id` varchar(32) NOT NULL COMMENT '订单号',"
                +"`username` varchar(64) NOT NULL COMMENT 'pptv用户名',"
                +"`user_id` varchar(20) NOT NULL COMMENT '快照：用户ID',"
                +"`phone_number` varchar(20) DEFAULT NULL COMMENT '快照：手机号码',"
                +"`prize_pool_id` int(11) unsigned NOT NULL COMMENT '快照：奖品池ID',"
                +"`prize_item_id` int(11) unsigned NOT NULL COMMENT '快照：奖品项ID',"
                +"`name` varchar(64) DEFAULT NULL COMMENT '快照：奖品名称',"
                +"`memo` varchar(3000) DEFAULT NULL COMMENT '快照：描述',"
                +"`prize_type` tinyint(3) NOT NULL DEFAULT '10' COMMENT '快照：奖品类型，默认空奖',"
                +"`prize_type_ext` json DEFAULT NULL COMMENT '快照：奖品属性扩展（根据奖品类型区分）',"
                +"`prize_item_ext` json DEFAULT NULL COMMENT '快照：奖品项扩展字段',"
                +"`state` tinyint(3) NOT NULL DEFAULT '0' COMMENT '奖品状态：0未领奖，1已领奖',"
                +"`invoke_state` tinyint(3) NOT NULL DEFAULT '0' COMMENT '接口调用状态：0:初使状态，1:未调用，2：MQ，3：调用成功，4:调用失败',"
                +"`message` varchar(255) DEFAULT NULL COMMENT '接口调用消息',"
                +"`award_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '领取奖品的时间',"
                +" PRIMARY KEY (`id`),"
                +" UNIQUE KEY `award_record_s_o_p` (`username`,`syscode`,`activity_id`,`order_id`,`prize_item_id`)"
                +") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4";
        
        
        
        
        
        
        
        
        
        
        
//        String pre = "ALTER TABLE ";
//        String aft = " MODIFY COLUMN memo VARCHAR(1025) COMMENT '快照：描述'";
        
        for(int i = 0; i < 256; i++){
            sb.append(pre).append(i).append(aft).append(";").append(System.getProperty("line.separator"));
        }
        
        //=======================写文件===================
        System.out.println("================开始写入=============");
        try{
            File file = new File("D:/TempFile/test.sql");
            PrintStream ps = new PrintStream(new FileOutputStream(file));
            ps.println(title + System.getProperty("line.separator") + sb.toString());// 往文件里写入字符串
        }catch(Exception e){
            e.printStackTrace();
        }
        
        System.out.println("================结束=============");
    }
}
