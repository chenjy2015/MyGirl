package app.originality.com.originality.util;

import android.content.Context;
import android.text.TextUtils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class StringUtils {

    private static final String TAG = "StringUtils";

    public static boolean isNullOrEmpty(Object value) {
        return (value == null) || ("".equals(value)) || ("null".equals(value));
    }


    public static boolean isTimeOk(String textStr) {


        return false;
    }

    /**
     * 验证手机格式
     * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
     * 联通：130、131、132、152、155、156、185、186
     * 电信：133、153、180、189、（1349卫通）
     * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
     */
    public static boolean isMobileNO(String mobiles) {
        //"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        String telRegex = "[1][34578]\\d{9}";
        if (TextUtils.isEmpty(mobiles)) return false;
        else return mobiles.matches(telRegex);
    }


    /**
     * 判断当前号码是否可以拨打
     *
     * @param phoneNumber
     * @return
     */
    public static boolean isPhoneNumberValid(String phoneNumber) {
        boolean isValid = false;

        isValid = isMobileNO(phoneNumber);
        if (!isValid) {
            isValid = isPhoneNumberEmergencyPhone(phoneNumber);
            if (!isValid) {
                isValid = isOperatorNumber(phoneNumber);
            }
        }
        return isValid;
    }


    // 判断是否是紧急求救电话号码
    public static boolean isPhoneNumberEmergencyPhone(String phoneNumber) {
        boolean isValid = false;
        if (phoneNumber == null || phoneNumber.equals("")) {
            return isValid;
        }

        String number = phoneNumber.toString().trim();

        if (number.equals("999") || number.equals("112")
                || number.equals("110") || number.equals("120")
                || number.equals("119") || number.equals("122")) {

            isValid = true;
            return isValid;
        }

        return isValid;
    }

    // 判断是否是三大电信运营商客服电话
    public static boolean isOperatorNumber(String phoneNumber) {
        boolean isValid = false;
        if (phoneNumber == null || phoneNumber.equals("")) {
            return isValid;
        }

        String number = phoneNumber.toString().trim();

        if (number.equals("10086") || number.equals("10010")
                || number.equals("10000")) {
            isValid = true;
            return isValid;
        }
        return isValid;
    }

    /**
     * 中文转码
     *
     * @param str
     * @return
     */
    public static String urlEncoder(String str) {
        if (isNullOrEmpty(str))
            return "";
        String ret = "";
        try {
            ret = URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 中文解码
     *
     * @param str
     * @return
     */
    public static String urlDecoder(String str) {
        if (isNullOrEmpty(str))
            return "";
        String ret = "";
        try {
            ret = URLDecoder.decode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ret;
    }

    public static String list2Str(ArrayList<String> list) {
        String result = ""; // 's','s','s'
        StringBuffer sb = new StringBuffer();
        for (String s : list) {
            sb.append(",");
            sb.append(s);
        }
        if (sb != null) {
            result = sb.deleteCharAt(0).toString();
        }
        LogOut.d(TAG, "list2Str:" + result);
        return result;
    }

    public static void showShortToast(Context ctx, String message) {
        ToastUtils.showBigVioletToast(ctx, message);
    }

    public static long convertString2long(String val) {
        long result = 0;
        if (isNullOrEmpty(val))
            return result;
        try {
            result = Long.parseLong(val);
        } catch (NumberFormatException e) {
        }

        return result;
    }

    /**
     * 得到文件名
     *
     * @param url
     * @return
     */
    public static String getFileNa(String url) {
        return url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("."));
    }

    /**
     * 得到文件後綴名
     *
     * @param url
     * @return
     */
    public static String getFileEx(String url) {
        return url.substring(url.lastIndexOf("."));
    }

    public static boolean removeFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            boolean bool = file.delete();
            LogOut.i("deletebool", "delete" + bool);
            return bool;
        }
        return false;
    }

    /**
     * 截取字符串
     *
     * @param arrStr
     * @param mSplit 截取表示 ，注意 转义 \\
     * @param i
     * @return
     */
    public static String getSplitStr(String arrStr, String mSplit, int i) {

        String result = "";
        if (arrStr != null && !"".equals(arrStr)) {
            String[] tempStr = arrStr.split(mSplit);
            if (tempStr != null && tempStr.length > i) {
                result = tempStr[i];
            }
        }
        return result;
    }

    /**
     * 检查是否合法的电话号码
     *
     * @param phoneNumber
     * @return
     */
    // public static boolean isPhoneNumberValid(String phoneNumber) {
    //
    // boolean isValid = false;
    // String expression = "^//(?(//d{3})//)?[- ]?(//d{3})[- ]?(//d{5})$";
    // String expression2 = "^//(?(//d{3})//)?[- ]?(//d{4})[- ]?(//d{4})$";
    // CharSequence inputStr = phoneNumber;
    //
    // Pattern pattern = Pattern.compile(expression);
    //
    // Matcher matcher = pattern.matcher(inputStr);
    //
    // Pattern pattern2 = Pattern.compile(expression2);
    //
    // Matcher matcher2 = pattern2.matcher(inputStr);
    //
    // if (matcher.matches() || matcher2.matches()) {
    // isValid = true;
    // }
    //
    // return isValid;
    //
    // }

    /**
     * String 转 Double
     */
    public static double convertString2Double(String val) {
        double result = 0;
        if (isNullOrEmpty(val))
            return result;
        try {
            result = Double.parseDouble(val);
        } catch (NumberFormatException e) {
        }

        return result;
    }

    /**
     * 限定字符长度
     *
     * @param content     源字符串
     * @param checkLength 限定长度 如没有限定长度 传 0
     * @return 返回 限定长度字符串 +" .... "
     */
    public static String checkStringLength(String content, int checkLength) {
        String result = "";
        if (content == null || content.equals("null")) {
            return result;
        }

        if (content.length() > checkLength) {
            if (checkLength > 0) {
                result = content.substring(0, checkLength) + "...";
            } else {
                result = result + "...";
            }
        } else {
            result = content == null ? "" : content;
        }
        return result;
    }

    /**
     * 正则表达式 判断是否纯数字
     */
    public static int ContentType(String content) {
        Pattern p = Pattern.compile("^[0-9]*$");
        Matcher m = p.matcher(content);
        if (m.matches()) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * 字符串转Double类型
     */
    public static double parseDouble(String content) {
        double d = 0.0;
        if (content == null || content.equals("")) {
            return d;
        }

        try {
            d = Double.parseDouble(content);
        } catch (Exception e) {
            LogOut.e(TAG, content + "parseDouble Exception");
        }
        return d;
    }

    /**
     * 判断字符串是否可用 返回空字符串或字符串本身
     */
    public static String checkStringIsNull(String content) {
        if (content == null || content.equals("null")) {
            return "";
        } else {
            return content;
        }
    }

    /**
     * 获取文件名称并进行中文转码 utf-8
     */
    public static String getFileName(String filePath) {
        File file = new File(filePath);
        String fileName = urlEncoder(file.getName());
        return fileName;
    }

    ;

    public static String convertInteger2String(int val) {
        if (val == 0) {
            return "";
        }
        return String.valueOf(val);
    }

    public static String convertDouble2String(double val) {
        if (val == 0) {
            return "";
        }
        return String.valueOf(val);
    }

    public static int convertString2Integer(String val) {
        int result = 0;
        if (isNullOrEmpty(val))
            return result;
        try {
            result = Integer.parseInt(val);
        } catch (NumberFormatException e) {
        }

        return result;
    }


    // 替换、过滤特殊字符
    public static String StringFilter(String str) throws PatternSyntaxException {
        String filterStr = "";
        Matcher m = null;
        try {
            str = str.replaceAll("【", "[").replaceAll("】", "]")
                    .replaceAll("！", "!");// 替换中文标号
            String regEx = "[『』]"; // 清除掉特殊字符
            Pattern p = Pattern.compile(regEx);
            m = p.matcher(str);
            filterStr = m.replaceAll("").trim();
        } catch (PatternSyntaxException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filterStr;
    }

    // 将textview中的字符全角化。即将所有的数字、字母及标点全部转为全角字符，
    // 使它们与汉字同占两个字节，这样就可以避免由于占位导致的排版混乱问题
    public static String ToDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }

    /**
     * @param @param  mContext
     * @param @param  code
     * @param @return
     * @return String
     * @throws
     * @Description: TODO(得到功能验证信息字典排序后的MD5加密内容)
     * @Title: getFunVerifyCode
     * @Author：gonghg @CreateDate：2014-3-25 下午5:39:33
     * @ModifiedBy： @ModifiedDate:  2014-3-25 下午5:39:33
     */
    public static String getCodeAppKeyMd5(Context mContext, String appKey, String code) {
        return new MD5().getMD5(code + appKey);
    }

    /**
     * ^ 匹配一行的开头位置
     * (?![0-9]+$) 预测该位置后面不全是数字
     * (?![a-zA-Z]+$) 预测该位置后面不全是字母
     * [0-9A-Za-z] {6,12} 由6-12位数字或这字母组成
     * `~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）&mdash;—|{}【】‘；：”“'。，、？ 允许输入特殊字符
     */
    public static boolean Passwordcheck(String pwd) {
        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[`~!@#$^&*()=|{}':;',\\\\[\\\\].<>/?~！@#￥……&*（）&mdash;—|{}【】‘；：”“'。，、？0-9A-Za-z]{8,12}$";
        return pwd.matches(regex);
    }

    /**
     * 密码限制 数字+字母 6-12位 首字母为大写
     * @param pwd
     * @return
     */
    public static boolean checkPassword(String pwd){
        String regex = "^(?=.*?[A-Z])(?=.*?[0-9])[a-zA-Z0-9]{6,12}$";
        return pwd.matches(regex);
    }

    public static boolean checkAccount(String act){
        String regex = "([a-zA-Z0-9]{6,12})";
        return act.matches(regex);
    }

    //纯中文判断
    public static boolean checkName(String s) {
        s = new String(s.getBytes());//用GBK编码
        String pattern = "[\u4e00-\u9fa5]+";
        Pattern p = Pattern.compile(pattern);
        Matcher result = p.matcher(s);
        return result.matches(); //是否含有中文字符
    }

    //    public boolean checkfilename(String s){
//        s=new String(s.getBytes());//用GBK编码
//        String pattern="[\u4e00-\u9fa5]+";
//        Pattern p=Pattern.compile(pattern);
//        Matcher result=p.matcher(s);
//        return result.find(); //是否含有中文字符
//    }
    public static boolean hasFullChar(String str) {
        if (str.getBytes().length == str.length()) {
            return false;
        }
        return true;
    }

    public static boolean isLetterAndNum(String str) {
        String strExp = "/^[A-Za-z0-9]+$/";
        return strExp.matches(str);
    }

    //包含中文
    public static boolean isContainChinese(String str) {

        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否为纯数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 判断是否为纯字母
     *
     * @param str
     * @return
     */
    public static boolean isChart(String str) {
        Pattern pattern = Pattern.compile("[A-za-z]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 密码检测
     *
     * @param passwrod
     * @return
     */
    public static String checkPasswrod(String passwrod) {
        String error = "";
        //第一步判断 是否是数字+英文 并且长度在6-12位之间
        boolean isSplit = StringUtils.Passwordcheck(passwrod);
        int type = 0; //type =0 数字 type =1 字符
        if (StringUtils.isNumeric(passwrod.substring(0, 1))) {
            type = 0;
        } else if (StringUtils.isChart(passwrod.substring(0, 1))) {
            type = 1;
        }
        //第二步 遍历字符串 看同一类型的字符是否超过3个
        int count = 0;
        if (isSplit) {
            for (int i = 0; i < passwrod.length(); i++) {
                if (count > 3) {
                    break;
                }
                String s1 = passwrod.substring(i, i + 1 >= passwrod.length() - 1 ? i : i + 1);
                //如果为数字类型 则判断为同一类型的字符 加1
                int t = 0;
                if (StringUtils.isNumeric(s1)) {
                    t = 0;
                } else if (StringUtils.isChart(s1)) {
                    t = 1;
                }
                //如果上个字符类型与当前类型一直 则相同类型数量+1 否则清零
                if (type == t) {
                    count++;
                } else {
                    count = 0;
                }
                type = t;
            }
        }
        if (count > 2) {
            error = "密码字符 不能连续输入字母或数字 3个以上";
        }
        return error;
    }


    // 过滤特殊字符
    public static String StringFilter2(String str) throws PatternSyntaxException {
        try {
            // 只允许字母和数字
            // String   regEx  =  "[^a-zA-Z0-9]";
            // 清除掉所有特殊字符
            String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(str);
            return m.replaceAll("").trim();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }
}
