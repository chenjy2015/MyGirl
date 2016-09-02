package app.originality.com.originality.util.media;

import java.io.Serializable;

public interface MediaPlayerProgressListenner extends Serializable{

    public void onPlayerStart(int duration);

    public void onPlayerChange(int currentPosition);

    public void onPlayerPause();

    public void onPlayerStop();


}