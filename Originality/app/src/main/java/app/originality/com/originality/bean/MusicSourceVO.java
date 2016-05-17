package app.originality.com.originality.bean;

public class MusicSourceVO {

    private String userPoint;           //匹配积分值

    private String voicePath;           //文件网络地址

    private String voiceFileName;       //文件保存到本地名称

    private String voiceLocationPath;   //文件保存到本地路径

    public String getVoiceLocationPath() {
        return voiceLocationPath;
    }

    public void setVoiceLocationPath(String voiceLocationPath) {
        this.voiceLocationPath = voiceLocationPath;
    }

    public String getVoiceFileName() {
        return voiceFileName;
    }

    public void setVoiceFileName(String voiceFileName) {
        this.voiceFileName = voiceFileName;
    }

    public String getUserPoint() {
        return userPoint;
    }

    public void setUserPoint(String userPoint) {
        this.userPoint = userPoint;
    }

    public String getVoicePath() {
        return voicePath;
    }

    public void setVoicePath(String voicePath) {
        this.voicePath = voicePath;
    }
}