package app.originality.com.originality.util.media;

import java.io.Serializable;

/**
 * @author cjy
 * @version V1.0
 * @company Chenjy_Studio
 * @Description 多媒体播放进度回调
 * @date 2016/9/3 11:50
 */
public interface MediaPlayerProgressListenner extends Serializable {

    public void onPlayerStart(int duration);

    public void onPlayerChange(int currentPosition);

    public void onPlayerPause();

    public void onPlayerStop();


}