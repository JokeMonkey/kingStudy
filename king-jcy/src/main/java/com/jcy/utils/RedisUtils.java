package com.jcy.utils;

import redis.clients.jedis.Jedis;

public class RedisUtils {
    private static Jedis jedis = null;
    
    static{
        jedis = new Jedis("127.0.0.1", 6379);
    }
    
    
    /**
     * 功能描述: 
     * 
     * @param key
     * @param value
     * @param seconds
     * @return String
     * @version 2.0.0
     * @author yaoyaowang
     */
    public static String set(String key, String value, int seconds){
        String result = null;
        
        try{
            result = jedis.set(key, value);
            jedis.expire(key, seconds);
        }catch(Exception e){
            e.printStackTrace();
            result = "NO";
        }
        
        return result;
    }
    
    
    public static String get(String key){
        String result = null;
        
        try{
            result = jedis.get(key);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return result;
    }
    
}
