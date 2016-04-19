package app.originality.com.originality.bean;

import java.io.Serializable;

/**
 * @author cjy
 * @version V1.0
 * @company Chenjy_Studio
 * @Description 相册列表
 * @date 2016/4/18
 */
public class PhotoGroupVO implements Serializable {

    private String groupUrl;            //相册图片 网络地址
    private String groupPath;           //相册图片 本地地址
    private String groupDescription;    //相册说明
    private String groupId;             //相册唯一标识 ID

    public String getGroupUrl() {
        return groupUrl;
    }

    public void setGroupUrl(String groupUrl) {
        this.groupUrl = groupUrl;
    }

    public String getGroupPath() {
        return groupPath;
    }

    public void setGroupPath(String groupPath) {
        this.groupPath = groupPath;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}