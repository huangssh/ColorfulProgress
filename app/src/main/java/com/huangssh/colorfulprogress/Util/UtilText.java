package com.huangssh.colorfulprogress.Util;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串操作工具包
 *
 * @author zhuofq
 * @createTime 2014-12-3 下午11:39:46
 */
public class UtilText {

    /**
     * 去除字符串中所有的空格
     *
     * @param res
     * @return
     */
    public static String trim(String res) {

        if (!TextUtils.isEmpty(res)) {
            Pattern pattern = Pattern.compile("\\s*|\t|\r|\n");
            Matcher matcher = pattern.matcher(res);
            res = matcher.replaceAll("");
            return res;
        }
        return res;
    }

    /**
     * 将字符串以一个空格来分割
     *
     * @param str
     * @return String[]
     * @author zhuofq
     */
    public static String[] splitBySpace(String str) {
        return str.split(" ");
    }

    /**
     * 判断一个字符串是否都为字母
     *
     * @param str
     * @return boolean
     * @author zhuofq
     */
    public static boolean isLetters(String str) {
        return str.matches("^[A-Za-z]+$");
    }

    /**
     * 得到全拼或简拼
     *
     * @param str  字符串
     * @param type 全拼还是简拼
     * @return String
     * @author zhuofq
     */
    public static String getString(String str, int type) {
        str = str.replaceAll("   ", "");
        String[] strs = splitBySpace(str);

        String[] newStrs = new String[strs.length];
        int j = 0;
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].length() > 0) {
                String firstLetter = strs[i].substring(0, 1);
                if (isLetters(firstLetter)) {
                    // type=0 out jp
                    if (type == 0) {
                        newStrs[j] = firstLetter;
                    }
                    // type=1 out qp
                    else {
                        newStrs[j] = strs[i];
                    }
                    j++;
                }
            }
        }
        StringBuffer sb = new StringBuffer();
        for (int k = 0; k < newStrs.length; k++) {
            if (newStrs[k] != null) {
                sb.append(newStrs[k]);
            }
        }
        return sb.toString();
    }

    /**
     * 获取当前省市，判断是否“西藏”或"新疆"
     *
     * @param mContext
     * @return boolean
     * @author jiangwenxin
     * @createTime 2014年8月14日 下午4:26:00
     */
    public static boolean isProvinceXJorXZ(Context mContext) {
        // CitysManager mCitysManager = new CitysManager(mContext);
        // String sid = Toolkits.getSID(mContext);
        // if (sid!=null){
        // CityInfo mCityInfo = mCitysManager.getCityBySidCodeCode(sid);
        // if(mCityInfo!=null && ("西藏自治区".equals(mCityInfo.getProvName()) ||
        // "新疆维吾尔自治区".equals(mCityInfo.getProvName()))){
        // return true;
        // }
        // }
        // zhuofq 4.5紧急发布前屏蔽.
        return false;
    }

    /**
     * 去掉小数后面的0
     *
     * @param old
     * @return String
     * @author zhangyi
     * @createTime 2014-12-1 上午9:56:23
     */
    public static String remove0(String old) {
        try {
            double num = Double.parseDouble(old);
            int intnum = (int) num;
            if (num == intnum) {
                return String.valueOf(intnum);
            } else {
                return String.valueOf(num);
            }
        } catch (Exception e) {
            return old;
        }
    }

    /**
     * 判断input是否空指针,"", "null"
     *
     * @param str
     * @return boolean
     * @author zhuofq
     */
    public static boolean isEmptyOrNull(String str) {
        return TextUtils.isEmpty(str) ? true : str.trim().toLowerCase(Locale.US).equals("null");
    }

    /**
     * 余额单位转换保留小数点后两位。话费超十万的，显示到个位四舍五入。
     *
     * @param fee 话费
     * @return
     * @author zhangyi
     * @createTime 2014年9月22日 下午3:32:40
     */
    public static String getFee(String fee) {
        String strFee = "";
        Double douFee;
        try {
            douFee = Double.valueOf(fee);
            if ((douFee / 100000) < 1) {// 话费不超过十万，显示接口返回数据
                strFee = String.format("%.2f", douFee);
            } else if ((douFee / 100000) >= 1) {// 话费超十万的，显示到个位
                strFee = String.format("%.0f", douFee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strFee;
    }

    /**
     * url 按utf-8进行编码
     *
     * @param str
     * @return String
     * @author zhuofq
     */
    public static String urlDecoder(String str) {
        URLDecoder ud = new URLDecoder();

        try {
            return ud.decode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 高亮显示手机号码的某些部分
     *
     * @param input
     * @param hlstart
     * @param hlend
     * @return SpannableString
     * @author zhuofq
     */
    public static SpannableString splitPhoneNumber(String input, int hlstart,
                                                   int hlend) {
        if (input == null || input.length() != 11) {
            return new SpannableString(input);
        }

        try {
            String splitNumbers = input.substring(0, 3) + " "
                    + input.substring(3, 7) + " " + input.substring(7, 11);
            SpannableString sp = new SpannableString(splitNumbers);
            int highStart = getActualPosition(hlstart);
            int highEnd = getActualPosition(hlend);
            if (highStart == -1 || highEnd == -1 || hlend <= hlstart) {
                return sp;
            }
            sp.setSpan(new ForegroundColorSpan(0xFFF16651), highStart, highEnd,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return sp;
        } catch (Exception e) {
            e.printStackTrace();
            return new SpannableString(input);
        }
    }

    /**
     * 获取实际位置
     *
     * @param position
     * @return
     * @author zhuofq
     */
    private static int getActualPosition(int position) {
        if (position >= 0 && position < 3) {
            return position;
        } else if (position >= 3 && position < 7) {
            return position + 1;
        } else if (position >= 7 && position < 12) {
            return position + 2;
        } else {
            return -1;
        }
    }

    /**
     * 中文字体加粗
     *
     * @param tv
     * @author zhuofq
     */
    public static void setTextBold(TextView tv) {
        TextPaint tp = tv.getPaint();
        tp.setFakeBoldText(true);
    }

    /**
     * 判断号码是否为电信号码
     *
     * @param input
     * @return boolean
     * @author zhuofq
     */
    public static boolean isCtPhoneNum(String input) {
        Pattern pattern = Pattern.compile("^1(33|53|80|81|89)\\d{8}$");
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    /**
     * 从url中获取文件名
     *
     * @param url
     * @return String
     * @author zhuofq
     */
    public static String parseFileNameFromUrl(String url) {
        if (url == null)
            return null;

        try {
            return url.substring(url.lastIndexOf("/") + 1, url.length());
        } catch (Exception e) {
            return null;
        }
    }

    private static final char HEX_DIGITS[] = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final String TAG = "Toolkits";

    /**
     * 获取十六进制字符串
     *
     * @param b
     * @return String
     * @author zhuofq
     */
    public static String toHexString(byte[] b) {
        // String to byte
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            sb.append(HEX_DIGITS[(b[i] & 0xf0) >>> 4]);
            sb.append(HEX_DIGITS[b[i] & 0x0f]);
        }
        return sb.toString();
    }

    private final static Pattern emailer = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
//    private final static Pattern phone = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");

    /**
     * 判断给定字符串是否空白串 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     *
     * @param input
     * @return boolean
     * @author zhuofq
     */
    public static boolean isEmpty(CharSequence input) {
        if (input == null || input.toString().trim().length() == 0)
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    /**
     * 是否超出最大长度
     *
     * @param str
     * @param minLength
     * @return
     * @作者 Liujun
     * @创建时间 2015-10-26 上午9:53:32
     */
    public static boolean isOutMaxLength(String str, int minLength) {

        if (isEmpty(str) || str.length() < minLength) {
            return true;
        }

        return false;
    }

    /**
     * 判断是不是一个合法的电子邮件地址
     *
     * @param email
     * @return boolean
     * @author zhuofq
     */
    public static boolean isEmail(CharSequence email) {
        if (isEmpty(email))
            return false;
        return emailer.matcher(email).matches();
    }

//    /**
//     * 判断是不是一个合法的手机号码
//     */
//    public static boolean isPhone(CharSequence phoneNum) {
//        if (isEmpty(phoneNum))
//            return false;
//        return phone.matcher(phoneNum).matches();
//    }

    /**
     * 字符串转整数
     *
     * @param str
     * @param defValue
     * @return int
     * @author zhuofq
     */
    public static int toInt(String str, int defValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
        }
        return defValue;
    }

    /**
     * 对象转整
     *
     * @param obj
     * @return int 转换异常返回 0
     * @author zhuofq
     */
    public static int toInt(Object obj) {
        if (obj == null)
            return 0;
        return toInt(obj.toString(), 0);
    }

    /**
     * String转long
     *
     * @param obj
     * @return long 转换异常返回 0
     * @author zhuofq
     */
    public static long toLong(String obj) {
        try {
            return Long.parseLong(obj);
        } catch (Exception e) {
        }
        return 0;
    }

    /**
     * String转double
     *
     * @param obj
     * @return double 转换异常返回 0
     * @author zhuofq
     */
    public static double toDouble(String obj) {
        try {
            return Double.parseDouble(obj);
        } catch (Exception e) {
        }
        return 0D;
    }

    /**
     * 字符串转布尔
     *
     * @param b
     * @return boolean 转换异常返回 false
     * @author zhuofq
     */
    public static boolean toBool(String b) {
        try {
            return Boolean.parseBoolean(b);
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * 判断一个字符串是不是数字
     *
     * @param str
     * @return boolean
     * @author zhuofq
     */
    public static boolean isNumber(CharSequence str) {
        try {
            Integer.parseInt(str.toString());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * byte[]数组转换为16进制的字符串。
     *
     * @param data 要转换的字节数组。
     * @return String 转换后的结果。
     * @author zhuofq
     */
    public static final String byteArrayToHexString(byte[] data) {
        StringBuilder sb = new StringBuilder(data.length * 2);
        for (byte b : data) {
            int v = b & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase(Locale.getDefault());
    }

    /**
     * 16进制表示的字符串转换为字节数组。
     *
     * @param s 16进制表示的字符串
     * @return byte[] 字节数组
     * @author zhuofq
     */
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] d = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            // 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个进制字节
            d[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character
                    .digit(s.charAt(i + 1), 16));
        }
        return d;
    }


    /**
     * 判断name的长度是否大于maxleng true：则返回 前maxLeng个字符，其他则已...表示
     *
     * @param name    需要判断的字符
     * @param maxLeng 字符显示的个数
     * @param defoStr 默认显示内容
     * @return String
     * @作者 Liujun
     * @创建时间 2015-8-4 上午11:02:52
     */
    public static String getShortName(String name, int maxLeng, String defoStr) {
        String fixName = isEmpty(defoStr) ? "用户" : defoStr;// 显示4个汉字,超过4个汉字,这显示前三再加上...

        if (name == null)
            return fixName;

        if (name.length() > 0 && name.length() <= maxLeng) {
            fixName = name;
        } else if (name.length() > maxLeng) {
            fixName = name.substring(0, maxLeng - 1) + "…";
        }

        return fixName;
    }

    /**
     * 字符串替换函数
     *
     * @param string       被替换字符串
     * @param target       要替换的字段
     * @param value        替换的内容
     * @param defaultValue 默认值
     * @return
     * @作者 zhangyi
     * @创建时间 2015-8-27 下午2:58:16
     * @版本
     */
    public static String replaceString(String string, String target, String value, String defaultValue) {
        if (string != null && string.contains(target)) {
            if (UtilText.isEmptyOrNull(value))
                value = defaultValue;
            string = string.replace(target, value);
        }
        return string;
    }

    /**
     * 给字符串添加CDATA的壳防止在通讯的时候被XML转译
     *
     * @return
     * @作者 zhangyi
     * @创建时间 2015-12-10 下午12:40:45
     * @版本
     */
    public static String addCDATA(String string) {
        return "<![CDATA[" + string + "]]>";
    }


    /**
     * 对链接打开掌厅的URL做189.cn的正则匹配，符合条件的才允许打开
     * http://xxx.189.cn:8080/oooo；http://www.xxx.189.cn/aaa.bb.cc
     * http://www.xxx.xxxq.189.cn:8080/aaa.bb.cc；https://xxx.189.cn/oooo
     * 以上几种都是符合要求的
     *
     * @return
     * @作者 zhangyi
     * @创建时间 2016-07-05 下午19:38:45
     * @版本
     */
    public static boolean match189cn(String string) {
        Matcher matcher = Pattern.compile(
                "https?:(.+?)\\.189\\.cn(?::(\\d{2,5}))?/.*",
                Pattern.CASE_INSENSITIVE).matcher(string);
        return matcher.find();
    }

    /**
     * 格式化号码
     * @param phone
     * @return
     */
    public static String toPhoneNumFormat(String phone){
        if (UtilText.isEmptyOrNull(phone)){
            return "";
        }else{
            return phone.replace("+86", "").replace("-", "").replace(" ", "");
        }
    }

    /**
     * 验证邮箱
     * @param email
     * @return
     */
    public static boolean checkEmail(String email){
        boolean flag = false;
        try{
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        }catch(Exception e){
            flag = false;
        }
        return flag;
    }

}
