package app.originality.com.originality.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.SeekBar;

import app.originality.com.originality.R;

public class SeekBarProgress extends SeekBar {

    public SeekBarProgress(Context context) {
        super(context);
        init();
    }

    public SeekBarProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SeekBarProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
    }

}