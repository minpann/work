package com.mp.api.common.util;

import com.alibaba.fastjson.JSON;
import com.mp.api.common.exception.ParamException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.apache.commons.lang.StringUtils.isNotEmpty;

/**
 * Created by panmin on 16-12-23.
 */
public class RequestUtil {
    private static final String NULL_ERR = "request parameter [%s] can not be null";
    private static final String[] CLIENT_IP_HEADER_NAMES = {"x-real-ip",
            "X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP"};

    /**
     * 获取请求参数
     */
    public static String getParam(HttpServletRequest request, String paramName,
                                  boolean required) throws ParamException {
        String value = request.getParameter(paramName);
        if (required && StringUtils.isEmpty(value)) {
            throw new ParamException(String.format(NULL_ERR, paramName));
        }
        return value;
    }

    /**
     * 获取请求参数
     */
    public static String getParam(HttpServletRequest request, String paramName,
                                  boolean required, int minLen, int maxLen) throws ParamException {
        String value = request.getParameter(paramName);
        if (required && StringUtils.isEmpty(value)) {
            throw new ParamException(String.format(NULL_ERR, paramName));
        }
        if (StringUtils.isNotEmpty(value)) {
            int len = value.length();
            if (minLen > 0 && len < minLen) {
                throw new ParamException(
                        String.format(
                                "request parameter [%s] should be more than %d characters",
                                paramName, minLen));
            }
            if (len > maxLen) {
                throw new ParamException(
                        String.format(
                                "request parameter [%s] should be less than %d characters",
                                paramName, maxLen));
            }
        }
        return value;
    }

    /**
     * 获取请求参数(int类型)
     */
    public static Integer getIntParam(HttpServletRequest request,
                                      String paramName, boolean required) throws ParamException {
        String value = request.getParameter(paramName);
        if (required && StringUtils.isEmpty(value)) {
            throw new ParamException(String.format(NULL_ERR, paramName));
        }
        Integer result = null;
        if (StringUtils.isNotEmpty(value)) {
            try {
                result = new Integer(value);
            } catch (NumberFormatException e) {
                throw new ParamException(String.format(
                        "request parameter [%s] should be a int value",
                        paramName));
            }
        }
        return result;
    }

    /**
     * 获取请求参数(long类型)
     */
    public static Long getLongParam(HttpServletRequest request,
                                    String paramName, boolean required) throws ParamException {
        String value = request.getParameter(paramName);
        if (required && StringUtils.isEmpty(value)) {
            throw new ParamException(String.format(NULL_ERR, paramName));
        }
        Long result = null;
        if (StringUtils.isNotEmpty(value)) {
            try {
                result = new Long(value);
            } catch (NumberFormatException e) {
                throw new ParamException(String.format(
                        "request parameter [%s] should be a int value",
                        paramName));
            }
        }
        return result;
    }

    /**
     * 获取请求参数(double类型)
     */
    public static Double getDoubleParam(HttpServletRequest request,
                                        String paramName, boolean required) throws ParamException {
        String value = request.getParameter(paramName);
        if (required && StringUtils.isEmpty(value)) {
            throw new ParamException(String.format(NULL_ERR, paramName));
        }
        Double result = null;
        if (StringUtils.isNotEmpty(value)) {
            try {
                result = new Double(value);
            } catch (NumberFormatException e) {
                throw new ParamException(String.format(
                        "request parameter [%s] should be a double value",
                        paramName));
            }
        }
        return result;
    }

    /**
     * 获取请求参数(boolean类型)
     */
    public static Boolean getBooleanParam(HttpServletRequest request,
                                          String paramName, boolean required) throws ParamException {
        String value = request.getParameter(paramName);
        if (required && StringUtils.isEmpty(value)) {
            throw new ParamException(String.format(NULL_ERR, paramName));
        }
        Boolean result = null;
        if (StringUtils.isNotEmpty(value)) {
            try {
                result = new Boolean(value);
            } catch (NumberFormatException e) {
                throw new ParamException(String.format(
                        "request parameter [%s] should be true or false",
                        paramName));
            }
        }
        return result;
    }

    /**
     * 获取请求参数(array类型)
     */
    public static <T> List<T> getArrayParam(HttpServletRequest request,
                                            String paramName, boolean required, Class<T> clazz)
            throws ParamException {
        String value = request.getParameter(paramName);
        if (required && StringUtils.isEmpty(value)) {
            throw new ParamException(String.format(NULL_ERR, paramName));
        }
        List<T> result = null;
        if (StringUtils.isNotEmpty(value)) {
            try {
                result = JSON.parseArray(value, clazz);
            } catch (Exception e) {
                throw new ParamException(String.format(
                        "request parameter [%s] should be array of [%s]",
                        paramName, clazz));
            }
        }
        return result;
    }

    /**
     * 获取请求参数(int类型+大小值限制)
     */
    public static Integer getIntParam(HttpServletRequest request,
                                      String paramName, boolean required, int min, int max) {
        String value = request.getParameter(paramName);
        if (required && StringUtils.isEmpty(value)) {
            throw new ParamException(String.format(NULL_ERR, paramName));
        }
        Integer result = null;
        if (StringUtils.isNotEmpty(value)) {
            try {
                result = new Integer(value);
            } catch (NumberFormatException e) {
                throw new ParamException(String.format(
                        "request parameter [%s] should be a int value",
                        paramName));
            }
            // 大小值校验
            if (min > Integer.MIN_VALUE && result.intValue() < min) {
                throw new ParamException(
                        String.format(
                                "request parameter [%s] must be higher or equal to %d ",
                                paramName, min));
            }
            if (max < Integer.MAX_VALUE && result.intValue() > max) {
                throw new ParamException(String.format(
                        "request parameter [%s] must be lower or equal to %d",
                        paramName, max));
            }
        }
        return result;
    }

    /**
     * 解析请求来源的IP
     *
     * @param request
     * @return
     */
    public static String parseCallFromIP(final HttpServletRequest request) {
        for (String header : CLIENT_IP_HEADER_NAMES) {
            String value = request.getHeader(header);
            if (isNotEmpty(value) && !"unknown".equalsIgnoreCase(value)) {
                return value;
            }
        }
        return request.getRemoteAddr();
    }

    public static HttpServletRequest getServletRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        return request;
    }
}
