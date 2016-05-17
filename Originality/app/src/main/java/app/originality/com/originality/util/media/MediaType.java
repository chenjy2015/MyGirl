package app.originality.com.originality.util.media;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/3/17.
 */
public enum MediaType implements Serializable {

    MUSIC_INTERNER("music", MediaProvider.INTERNER),
    MUSIC_LOCATION("music", MediaProvider.LOCATION),
    MUSIC_ASSET("music", MediaProvider.ASSET),
    MUSIC_RAW("music", MediaProvider.RAW),
    VIDEO_INTERNER("video", MediaProvider.INTERNER),
    VIDEO_LOCATION("video", MediaProvider.LOCATION);


    /**
     * 媒体类型
     */
    private String mediaType;

    /**
     * 资源来源
     */
    private MediaProvider mediaProvider;

    /**
     * @param mediaType     媒体类型
     * @param mediaProvider 资源来源
     */
    private MediaType(String mediaType, MediaProvider mediaProvider) {
        this.mediaType = mediaType;
        this.mediaProvider = mediaProvider;
    }

}
