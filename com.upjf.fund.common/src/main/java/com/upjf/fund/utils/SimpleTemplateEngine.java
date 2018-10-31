package com.upjf.fund.utils;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.time.DateFormatUtils;

/**
 * 根据传入的对象值替换模板中的内容 替换标识：${name} ${id}
 */
public class SimpleTemplateEngine {

    private final static String GETMETHODPREF = "get";// get方法前缀

    private final static String TIMETEMPLATE = "yyyy-MM-dd HH:mm:ss";// 日期格式

    public static String replaceArgs(String template, Map<String, String> dataMap) {
        StringBuffer sb = new StringBuffer();

        try {
            Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}");
            Matcher matcher = pattern.matcher(template);
            while (matcher.find()) {
                // 键名标识
                String name = matcher.group(1);
                String value = (String) dataMap.get(name);
                if (value == null) {
                    value = "";
                }

                matcher.appendReplacement(sb, value);
            }
            matcher.appendTail(sb);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    public static String replaceArgs(String template, Object bean) {
        StringBuffer sb = new StringBuffer();

        try {
            Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}");
            Matcher matcher = pattern.matcher(template);
            while (matcher.find()) {
                // 键名标识
                String name = matcher.group(1);
                String value = getPropertiesValue(bean, name);
                if (value == null) {
                    value = "";
                }

                matcher.appendReplacement(sb, value);
            }
            matcher.appendTail(sb);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    // 获取JAVABEAN中的指定属性的值
    private static String getPropertiesValue(Object bean, String param) {
        String returnValue = "";
        try {
            StringBuilder sb = new StringBuilder(GETMETHODPREF);
            param = sb.append(param.substring(0, 1).toUpperCase()).append(param.substring(1)).toString();
            Method mt = bean.getClass().getMethod(param);
            Object obj = mt.invoke(bean);
            if (obj instanceof Date) {
                returnValue = DateFormatUtils.format((Date) obj, TIMETEMPLATE);
            } else {
                returnValue = String.valueOf(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnValue;
    }

}
