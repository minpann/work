package com.mp.api.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by panmin on 16-12-23.
 */
public class TypeUtil {
    /**
     * 日志记录
     */
    private static Logger logger = LoggerFactory.getLogger(TypeUtil.class);

    /**
     * 字符串转化为整数, 若转化失败,取defaultVal值
     *
     * @param strVal 字符串
     * @param defaultVal 转化失败后的默认值
     * @return 转化后的数值
     */
    public static Integer parseInt(String strVal, Integer defaultVal) {

        Integer intVal;
        try {
            intVal = Integer.parseInt(strVal);

            if (intVal <= 0) {
                intVal = defaultVal;
            }
        } catch (Exception e) {
            logger.error("parse failed, can't parse [" + strVal + "] to a int,error info is " + e);
            intVal = defaultVal;
        }

        return intVal;
    }

    /**
     * 字符串转化为整数, 若转化失败,取defaultVal值
     *
     * @param strVal 字符串
     * @param defaultVal 转化失败后的默认值
     * @return 转化后的数值
     */
    public static Integer parseOrgInt(String strVal, Integer defaultVal) {

        Integer intVal;
        try {
            intVal = Integer.parseInt(strVal);
        } catch (Exception e) {
            logger.error("parse failed, can't parse [" + strVal + "] to a int,error info is " + e);
            intVal = defaultVal;
        }

        return intVal;
    }

    /**
     * 字符串转化为长整数, 若转化失败,取defaultVal值
     *
     * @param strVal 字符串
     * @param defaultVal 转化失败后的默认值
     * @return 转化后的数值
     */
    public static Long parseLong(String strVal, Long defaultVal) {

        Long longVal;
        try {
            longVal = Long.parseLong(strVal);

            if (longVal <= 0) {
                longVal = defaultVal;
            }
        } catch (Exception e) {
            logger.error("parse failed, can't parse [" + strVal + "] to a long,error info is " + e);
            longVal = defaultVal;
        }

        return longVal;
    }

    /**
     * 字符串转化为长boolean, 若转化失败,取defaultVal值
     *
     * @param strVal 字符串
     * @param defaultVal 转化失败后的默认值
     * @return 转化后的数值
     */
    public static Boolean parseBoolean(String strVal, Boolean defaultVal) {

        Boolean booleanVal;
        try {
            booleanVal = Boolean.parseBoolean(strVal);

        } catch (Exception e) {
            logger.error(
                    "parse failed, can't parse [" + strVal + "] to a boolean,error info is " + e);
            booleanVal = defaultVal;
        }

        return booleanVal;
    }

    /**
     * 字符串转化为short, 若转化失败,取defaultVal值
     *
     * @param strVal 字符串
     * @param defaultVal 转化失败后的默认值
     * @return 转化后的数值
     */
    public static Short parseShort(String strVal, Short defaultVal) {

        return parseInt(strVal, defaultVal.intValue()).shortValue();
    }
}
