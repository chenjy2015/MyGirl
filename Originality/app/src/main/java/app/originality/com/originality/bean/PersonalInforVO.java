package app.originality.com.originality.bean;

import java.io.Serializable;


/**
 * @author cjy
 * @version V1.0
 * @company Chenjy_Studio
 * @Description 个人资料
 * @date 2016/4/8
 */
public class PersonalInforVO implements Serializable {

    private String name;
    private String age;
    private String sex;
    private String label; //标签
    private String idea_home; //理想居所
    private String plan; //计划

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getIdea_home() {
        return idea_home;
    }

    public void setIdea_home(String idea_home) {
        this.idea_home = idea_home;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }
}