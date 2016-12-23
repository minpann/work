package com.mp.api.common.util;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by panmin on 16-12-23.
 */
public class QueryUtil {
    public static final long INVALID_DATE = 0L;

    /**
     * Solr字符转义处理，否则查询下列字符会报查询错误。 特殊字符串：+ – && || ! ( ) { } [ ] ^ ” ~ * ? : \ / 空格
     *
     * @param input 输入
     * @return 输出
     */
    public static String transformMetachar(String input) {
        StringBuffer sb = new StringBuffer();
        try {
            String regex = "[+/\\-&|!(){}\\[\\]^\"~*?:(\\)\\s\\\\]";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);
            while (matcher.find()) {
                matcher.appendReplacement(sb, "\\\\" + matcher.group());
            }
            matcher.appendTail(sb);
        } catch (Exception e) {
            // TODO
        }
        return sb.toString();
    }

    /**
     * 格式化时间
     *
     * @param date 日期
     * @return 格式化后的日期
     */
    public static String formatDate(Date date) {
        return formatDate(date, "yyyy-MM-dd");
    }

    /**
     * 格式化时间
     *
     * @param date 日期
     * @param format 格式
     * @return 格式化后的时间
     */
    public static String formatDate(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);

        if (date == null) {
            date = new Date();
        }

        return sdf.format(date);
    }

    /**
     * 检查一个日期字符串是否有效,若无效,则返回NULL
     *
     * @param dateStr 日期字符串
     * @return 相应的结果
     */
    public static long checkDate(String dateStr) {

        if (StringUtils.isBlank(dateStr)) {
            return INVALID_DATE;
        }

        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return INVALID_DATE;
    }
}
