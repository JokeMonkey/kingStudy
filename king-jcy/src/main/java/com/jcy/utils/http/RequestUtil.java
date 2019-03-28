/*
 * Copyright (C), 2002-2016, 苏宁易购电子商务有限公司
 * FileName: RequestUtil.java
 * Author:   guanyang/14050360
 * Date:     2016年8月16日 下午5:04:58
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.jcy.utils.http;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

/**
 * 功能描述：request工具类
 * 
 * @version 2.0.0
 * @author guanyang/14050360
 */
public class RequestUtil {

    private static final String HTTP                        = "http://";
    private static final String HTTPS                       = "https://";
    private static final int    DEFAULT_PORT                = 80;

    private static final String USER_AGENT                  = "user-agent";
    /**
     * userAgent中app标识
     */
    private static final String APP_FLAG                    = "SNEBUY-APP";

    private static final String DEFAULT_AJAX_REQUEST        = "XMLHttpRequest";

    private static final String DEFAULT_AJAX_REQUEST_HEADER = "X-Requested-With";

    private static final String DEFAULT_X_REQUEST_URL       = "x-request-url";

    private RequestUtil() {

    }

    /**
     * 功能描述: 验证是否是app调用，true是，false不是
     * 
     * @param request
     * @return
     * @version 2.0.0
     * @author guanyang/14050360
     */
    public static boolean validateApp(HttpServletRequest request) {
        String userAgent = request.getHeader(USER_AGENT);
        if (StringUtils.isNotBlank(userAgent) && userAgent.contains(APP_FLAG)) {
            return true;
        }
        return false;
    }

    /**
     * 功能描述: 验证是否是ajax请求，true是，false不是
     * 
     */
    public static boolean validateAjaxRequest(HttpServletRequest request) {
        return DEFAULT_AJAX_REQUEST.equalsIgnoreCase(request.getHeader(DEFAULT_AJAX_REQUEST_HEADER));
    }

    /**
     * 功能描述: 获取header中x-request-url值
     * 
     * @param request
     * @return
     * @version 2.0.0
     * @author guanyang/14050360
     */
    public static String getRequestUrlFromHeader(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        return request.getHeader(DEFAULT_X_REQUEST_URL);

    }

    /**
     * 功能描述: 通过header验证是否是https请求
     * 
     * @param request
     * @return
     * @version 2.0.0
     * @author guanyang/14050360
     */
    public static boolean validateHttpsFromHeader(HttpServletRequest request) {
        String requestUrlFromHeader = getRequestUrlFromHeader(request);
        return StringUtils.isNotBlank(requestUrlFromHeader) && requestUrlFromHeader.startsWith(HTTPS);
    }

    /**
     * 功能描述: 根据不同的协议组装不同的redirect路径（只适用于重定向到本系统的请求）
     * 
     * @param request
     * @param relativeRedirectUrl
     * @return
     * @version 2.0.0
     * @author guanyang/14050360
     */
    public static String buildRedirectUrlFromHeader(HttpServletRequest request,
                                                    String relativeRedirectUrl) {
        boolean flag = validateHttpsFromHeader(request);
        StringBuilder builder = new StringBuilder();
        if (flag) {
            builder.append(HTTPS);
        } else {
            builder.append(HTTP);
        }
        builder.append(request.getServerName());
        int port = request.getServerPort();
        if (port != DEFAULT_PORT) {
            builder.append(":").append(request.getServerPort());
        }
        builder.append(request.getContextPath());
        builder.append(relativeRedirectUrl);
        return builder.toString();
    }

    /**
     * 功能描述: 删除url协议头
     * 
     * @param sourceUrl
     * @return
     * @version 2.0.0
     * @author guanyang/14050360
     */
    public static String deleteUrlProtocol(String sourceUrl) {
        if (StringUtils.isBlank(sourceUrl)) {
            return null;
        }
        int index = sourceUrl.indexOf("//");
        if (index > 0) {
            return sourceUrl.substring(index);
        } else {
            return sourceUrl;
        }
    }

    /**
     * 功能描述: 组装协议头
     * 
     * @param sourceUrl 原url，支持http://，https://，//
     * @param isForceHttps 是否强制替换成https
     * @return
     * @version 2.0.0
     * @author guanyang/14050360
     */
    public static String wrapUrlProtocol(String sourceUrl,
                                         boolean isForceHttps) {
        if (StringUtils.isBlank(sourceUrl)) {
            return null;
        }
        boolean flag = sourceUrl.startsWith("//");
        if (isForceHttps) {
            return flag ? "https:" + sourceUrl : sourceUrl.replace(HTTP, HTTPS);
        } else {
            return flag ? "http:" + sourceUrl : sourceUrl;
        }
    }

    /**
     * 功能描述: 组装协议头
     * 
     * @param request 请求对象
     * @param sourceUrl 原url，支持http://，https://，//
     * @return
     * @version 2.0.0
     * @author guanyang/14050360
     */
    public static String wrapUrlProtocol(HttpServletRequest request,
                                         String sourceUrl) {
        boolean flag = validateHttpsFromHeader(request);
        return wrapUrlProtocol(sourceUrl, flag);

    }

}
