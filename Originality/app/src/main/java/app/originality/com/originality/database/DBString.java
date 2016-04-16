package app.originality.com.originality.database;

public class DBString {

    private static final String CREATE_TAB = " CREATE TABLE ";        //创建语句

    //用户表
    public static final String TABLE_NAME_USERINFOR = "USER_INFOR";        //表名称：userinfor


    /**
     * 创建用户表 存储用户信息JSON形式的字符串
     */
    public static final String CREATE_TABLE_USERINFOR = CREATE_TAB + TABLE_NAME_USERINFOR
            + "("
            + "USERINFOR_STR TEXT NOT NULL"
            + ")";

}