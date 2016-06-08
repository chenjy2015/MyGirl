package com.umeng.share.UmengSharedApplication.bean;

import java.io.Serializable;

/**
 * @author cjy
 * @version V1.0
 * @company
 * @Description 分享平台
 * @date 2016/6/8
 */
public class PlatformVO implements Serializable {

    private int icon;
    private String description;

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}