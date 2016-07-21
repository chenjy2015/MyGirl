package app.originality.com.originality.util;

public class ProgressCalcUtils {
    private int mSecond = 0;

    private final int HOUR = 60 * 60;

    private final int MINUTES = 60;


    public ProgressCalcUtils(int second) {
        mSecond = second;
    }

    public int getHours() {
        return mSecond / HOUR;
    }

    public int getMINUTES() {
        int hour = mSecond / HOUR;
        return (mSecond - hour * HOUR) / MINUTES;
    }

    public int getSecond() {
        return mSecond % MINUTES;
    }
}