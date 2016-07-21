package app.originality.com.originality.util.media;

import java.io.Serializable;

public interface MediaPlayerProgressListenner extends Serializable{

    public void onStart(int duration);

    public void onChange(int currentPosition);

    public void onPause();

    public void onStop();


}