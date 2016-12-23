package com.mp.api.common.util;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by panmin on 16-12-23.
 */
public class WebUtil {
    /**
     * 获取请求跌指定参数名的值，若无，则不需要出异常的情况下，返回默认值
     *
     * @param request      request
     * @param paramKey     param key
     * @param defaultValue default value
     * @return 结果
     */
    public static String parseStringParam(HttpServletRequest request, String paramKey,
                                          String defaultValue) {
        String value = request.getParameter(paramKey);
        if (StringUtils.isBlank(value)) {
            value = defaultValue;
        }

        return value;
    }

    /**
     * 处理整数值
     *
     * @param request      请求
     * @param paramKey     参数KEY
     * @param defaultValue 默认值
     * @param min          最小值
     * @param max          最大值
     * @return 结果
     */
    public static Integer parseIntParam(HttpServletRequest request, String paramKey,
                                        Integer defaultValue, Integer min, Integer max) {

        String value = request.getParameter(paramKey);
        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }
        try {
            Integer intVal = Integer.valueOf(value);

            if (min != null && intVal < min)
                intVal = min;
            if (max != null && intVal > max)
                intVal = max;

            return intVal;
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 处理整数值
     *
     * @param request      请求
     * @param paramKey     参数KEY
     * @param defaultValue 默认值
     * @param min          最小值
     * @param max          最大值
     * @return 结果
     */
    public static Long parseLongParam(HttpServletRequest request, String paramKey,
                                      Long defaultValue, Long min, Long max) {

        String value = request.getParameter(paramKey);
        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }
        try {
            Long lngValue = Long.valueOf(value);

            if (min != null && lngValue < min)
                lngValue = min;
            if (max != null && lngValue > max)
                lngValue = max;

            return lngValue;
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static Integer getInteger(HttpServletRequest request, String param) {
        String valueStr = request.getHeader(param);
        if (StringUtils.isEmpty(valueStr)) {
            return null;
        }

        Integer value = null;
        try {
            value = Integer.valueOf(valueStr);
        } catch (Exception e) {
        }
        return value;
    }

    public static Long getLong(HttpServletRequest request, String param) {
        String valueStr = request.getHeader(param);
        if (StringUtils.isEmpty(valueStr)) {
            return null;
        }

        Long value = null;
        try {
            value = Long.valueOf(valueStr);
        } catch (Exception e) {
        }
        return value;
    }
}
