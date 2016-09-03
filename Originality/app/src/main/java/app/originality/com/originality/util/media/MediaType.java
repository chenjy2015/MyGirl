package app.originality.com.originality.util.media;

import java.io.Serializable;


/**
 *
 * @company Chenjy_Studio
 * @Description 多媒体播放地址来源
 * @author cjy
 * @date 2016/3/17 11:51
 * @version V1.0
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
