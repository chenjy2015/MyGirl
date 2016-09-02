package app.originality.com.originality.bean;

public class MusicSourceVO {

    private String userPoint;           //匹配积分值
    private String voicePath;           //文件网络地址
    private String voiceFileName;       //文件保存到本地名称
    private String voiceLocationPath;   //文件保存到本地路径
    private String voiceName;           //曲名
    private String voiceSinger;         //歌手或创作者
    private String voiceYear;           //发行年代
    private String voiceArea;           //区域
    private String voiceSize;           //大小

    public String getVoiceArea() {
        return voiceArea;
    }

    public void setVoiceArea(String voiceArea) {
        this.voiceArea = voiceArea;
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

    public String getVoiceFileName() {
        return voiceFileName;
    }

    public void setVoiceFileName(String voiceFileName) {
        this.voiceFileName = voiceFileName;
    }

    public String getVoiceLocationPath() {
        return voiceLocationPath;
    }

    public void setVoiceLocationPath(String voiceLocationPath) {
        this.voiceLocationPath = voiceLocationPath;
    }

    public String getVoiceName() {
        return voiceName;
    }

    public void setVoiceName(String voiceName) {
        this.voiceName = voiceName;
    }

    public String getVoiceSinger() {
        return voiceSinger;
    }

    public void setVoiceSinger(String voiceSinger) {
        this.voiceSinger = voiceSinger;
    }

    public String getVoiceYear() {
        return voiceYear;
    }

    public void setVoiceYear(String voiceYear) {
        this.voiceYear = voiceYear;
    }

    public String getVoiceSize() {
        return voiceSize;
    }

    public void setVoiceSize(String voiceSize) {
        this.voiceSize = voiceSize;
    }
}