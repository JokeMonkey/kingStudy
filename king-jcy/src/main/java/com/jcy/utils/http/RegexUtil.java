package com.jcy.utils.http;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * 功能描述：正则检验工具类
 * 
 */
public class RegexUtil {

    private RegexUtil() {

    }

    /**
     * 功能描述: 手机号验证
     * 
     * @param str 待验证手机号
     * @return 验证通过返回true
     * @version 2.0.0
     * @author guanyang/14050360
     */
    public static boolean isMobile(String str) {
        return isMatch("^[1][3,4,5,7,8][0-9]{9}$", str);// 验证手机号
    }

    /**
     * 功能描述: 校验
     * 
     * @param str
     * @return boolean
     * @version 2.0.0
     * @author zhengkai/15050071
     */
    public static boolean isName(String str) {
        boolean flag = true;
        if (StringUtils.isBlank(str) || str.length() > 12) {
            flag = false;
        }
        return flag;
    }

    public static boolean validateName(String name) {
        if (StringUtils.isBlank(name) || name.indexOf("先生") != -1 || name.indexOf("小姐") != -1) {
            return false;
        }
        if (name.indexOf("女士") != -1 || name.indexOf("顾客") != -1) {
            return false;
        }
        return isMatch("^[\u4e00-\u9fa5]{2,12}$", name);// 中文字符：[\u4e00-\u9fa5]
    }

    /**
     * 功能描述: 校验标签内容，只能是1~6个中文字符，true表示通过，false不通过
     * 
     * @param tagContent
     * @return
     * @version 2.0.0
     * @author guanyang/14050360
     */
    public static boolean validateTag(String tagContent) {
        return isMatch("^[\u4e00-\u9fa5]{1,6}$", tagContent);// 中文字符：[\u4e00-\u9fa5]
    }

    /**
     * 功能描述: S码格式验证
     * 
     * @param scode
     * @return
     * @version 2.0.0
     * @author guanyang/14050360
     */
    public static boolean isScode(String scode) {
        return isMatch("^[0-9a-zA-Z]{16}$", scode);// 验证S码
    }

    /**
     * 功能描述: 邮箱验证
     * 
     * @param email 待验证邮箱
     * @return 验证通过返回true
     * @version 2.0.0
     * @author guanyang/14050360
     */
    public static boolean isEmail(String email) {
        return isMatch("^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$", email);
    }

    /**
     * 功能描述: 校验方法
     * 
     * @param regex
     * @param orginal
     * @return boolean
     * @version 2.0.0
     * @author zhengk
     */
    private static boolean isMatch(String regex,
                                   String orginal) {
        if (StringUtils.isBlank(orginal)) {
            return false;
        }
        return Pattern.matches(regex, orginal);
    }

    /**
     * 功能描述: 判断是否是数字或小数类型
     * 
     * @param orginal
     * @return boolean
     * @version 2.0.0
     * @author zhengk
     */
    public static boolean isNumber(String orginal) {
        return isMatch("^(-?\\d+)(\\.\\d+)?$", orginal);
    }

    /**
     * 功能描述: 判断是否是浮点数，若是则返回整数，若不是则返回为空
     * 
     * @return int
     * @version 2.0.0
     * @author zhengk
     */
    public static Integer double2Int(String orginal) {
        if (isNumber(orginal)) {
            return (int) Math.floor(Double.parseDouble(orginal));
        }
        return null;
    }

    /**
     * 功能描述: 判断url是否是https协议
     * 
     * @return boolean
     * @version 2.0.0
     * @author hulong/16091623
     */
    public static boolean isHttps(String url) {
//        (https://|http://)?([\w-]+\.)+[\w-]+(/[\w- ./?%&=]*)?
        return isMatch("(https://)?([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?", url);
    }

    /**
     * 功能描述: 判断url
     * 
     * @return boolean
     * @version 2.0.0
     * @author hulong/16091623
     */
    public static boolean isUrl(String url) {
        return isMatch("http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?", url);
    }
}
