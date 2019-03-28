/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: IpUtil.java
 * Author:   piaohailin
 * Date:     2018年6月7日 上午9:32:24
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.jcy.utils.http;

import javax.servlet.http.HttpServletRequest;

/**
 * 功能描述:
 * 
 * @version 2.0.0
 * @author piaohailin
 */
public class IpUtil {
    /**
     * 请求头名称：CDN
     */
    public static final String HEADER_NAME_CDN = "Cdn-Src-Ip";
    
    /**
     * 请求头名称：X-Real
     */
    public static final String HEADER_NAME_X_REAL = "X-Real-IP";
    
    /**
     * 请求头名称：X-Forwarded-For
     */
    public static final String HEADER_NAME_X_FORWARDED_FOR = "X-Forwarded-For";

    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    
    public static String getClientIP(HttpServletRequest request) {
        // 客户端IP
        String clientIP = "";

        // 检查Cdn-Src-Ip：如果取得了CDN的IP，直接返回
        clientIP = request.getHeader(HEADER_NAME_CDN);
        if (!isEmpty(clientIP)) {
            return clientIP;
        }

        // 检查X-Real-IP：如果取得了X-Real的IP，直接返回
        clientIP = request.getHeader(HEADER_NAME_X_REAL);
        if (!isEmpty(clientIP)) {
            return clientIP;
        }

        /*
         * 1.经过代理或者代理服务器以后，由于在客户端和服务之间增加了中间层，
         * 因此服务器无法直接拿到客户端的IP，服务器端应用也无法直接通过转发请求的地址返回 给客户端。
         * 2.但是在转发请求的HTTP头信息中，增加了X－FORWARDED－FOR信息，
         * 用以跟踪原有的客户端IP地址和原来客户端请求的服务器地址。
         * 3.如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP，
         * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
         */
        String forwordIPs = request.getHeader(HEADER_NAME_X_FORWARDED_FOR);
        if (!isEmpty(forwordIPs)) {
            // 存在多个IP时取第一个
            clientIP = forwordIPs.split(",")[0].trim();
            if (!isEmpty(clientIP)) {
                return clientIP;
            }
        }

        // 如果都没取到，直接取远程地址
        if (isEmpty(clientIP)) {
            clientIP = request.getRemoteAddr();
        }
        return clientIP;
    }
    
    /**
     * 功能描述: 判断ip是否是空
     * 
     * @param ip
     * @return boolean
     * @version 1.0.1
     * @author xiaowux
     * @create 2018年11月3日
     */
    public static boolean isEmpty(String ip){
        if (null == ip || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            return true;
        }
        return false;
    }
}
