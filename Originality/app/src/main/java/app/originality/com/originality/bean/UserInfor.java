package app.originality.com.originality.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class UserInfor implements Serializable {

    private String account;             //账号
    private String passWord;            //密码
    private String name;                //姓名
    private int age;                    //年龄
    private String sex;                 //性别
    private String[] hobbys;   //爱好
    private String[] iedeal;   //理想
    private String[] plans;    //计划

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String[] getHobbys() {
        return hobbys;
    }

    public void setHobbys(String[] hobbys) {
        this.hobbys = hobbys;
    }

    public String[] getIedeal() {
        return iedeal;
    }

    public void setIedeal(String[] iedeal) {
        this.iedeal = iedeal;
    }

    public String[] getPlans() {
        return plans;
    }

    public void setPlans(String[] plans) {
        this.plans = plans;
    }
}