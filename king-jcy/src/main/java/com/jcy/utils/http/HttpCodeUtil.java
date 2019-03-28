
package com.jcy.utils.http;

import java.net.URLDecoder;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 功能描述: http参数的解码与编码
 * 
 * @version 2.0.0
 * @author zhaoboyang
 */
public class HttpCodeUtil {
    protected static final Logger    logger   = LoggerFactory.getLogger(HttpCodeUtil.class);
    private static final String CHARSET_UTF8 = "UTF-8";
    /**
     * 功能描述: 解码
     * 
     * @param code
     * @return String
     * @version 2.0.0
     * @author zhaoboyang
     */
    public static String stringDecode(String code) {
        
        try {
            code = URLDecoder.decode(code, CHARSET_UTF8);
            code = code.replaceAll("[\\t\\n\\r]", "");
        } catch (Exception e) {
            logger.info("解码转换错误",e.getMessage(),e);
            
        }
        return code;
    }
    /**
     * 功能描述: 字符串编码
     * 
     * @param code
     * @return String
     * @version 2.0.0
     * @author zhaoboyang
     */
    public static String stringeEncode(String code) {
        try {
            code = URLEncoder.encode(code, CHARSET_UTF8);
        } catch (Exception e) {
            logger.info("编码转换错误",e.getMessage(),e);
            
        }
        return code;
    }
}
