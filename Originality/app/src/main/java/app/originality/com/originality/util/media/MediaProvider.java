package app.originality.com.originality.util.media;


/**
 * @author cjy
 * @version V1.0
 * @company Chenjy_Studio
 * @Description 多媒体播放类型
 * @date 2016/3/17 11:5
 */
public enum MediaProvider {
    INTERNER("interner"),
    LOCATION("location"),
    ASSET("asset"),
    RAW("raw");

    private String musicType;

    private MediaProvider(String musicType) {
        this.musicType = musicType;
    }
}