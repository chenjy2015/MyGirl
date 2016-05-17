package app.originality.com.originality.util.media;


/**
 * Created by Administrator on 2016/3/17.
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