package app.originality.com.originality.util.media;


/**
 * @author cjy
 * @version V1.0
 * @company Chenjy_Studio
 * @Description 模拟Activity声明周期方法
 * @date 2016/9/3 11:50
 */
public interface ActivityMethodInterface {

    public void onCreate();

    public void onStart();

    public void onRestart();

    public void onResume();

    public void onPause();

    public void onStop();

    public void onDestroy();
}