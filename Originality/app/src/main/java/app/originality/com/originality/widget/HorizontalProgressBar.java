package app.originality.com.originality.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import app.originality.com.originality.util.AndroidSystemHelper;
import app.originality.com.originality.util.ProgressCalcUtils;


/**
 * @author cjy
 * @version V1.0
 * @Description 自定义进度条
 * @date 2016/5/18
 */
public class HorizontalProgressBar extends View {

    private Context mContext;
    //    private Paint mPaint;                           //画笔
    private String mBackgroundPaintColor;           //背景画笔颜色
    private String mProgressPaintColor;             //进度条画笔颜色
    private String mProgressTextColor;              //进度条文字颜色
    private int mWidth;                             //进度条宽度
    private int mHeight;                            //进度条高度
    private int mMaxProgress = 100;                 //进度条最大值
    private int mProgress;                          //当前进度值
    private int mProgressType;                      //0 数值进度值%  1 时间进度值
    private int startX;
    private int startY;

    public HorizontalProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
//        mPaint = new Paint();
        mBackgroundPaintColor = "#FFFFFF";  // 设置画笔白色
        mProgressPaintColor = "#76B034";    // 设置进度条绿色
        mProgressTextColor = "#76B034";     // 设置进度条文字白色
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画出圆角矩形
        drawBackground(canvas);
        //画出内部进度
        drawProgress(canvas);
        // 绘制下标进度数字
        drawText(canvas);
    }

    /**
     * 绘制背景
     */
    private void drawBackground(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);// 设置是否抗锯齿
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);// 帮助消除锯齿
        paint.setColor(Color.parseColor(mBackgroundPaintColor));
        paint.setStrokeWidth(10);// 设置画笔宽度
        paint.setStyle(Paint.Style.STROKE); //设置为空心
        RectF r2 = new RectF();
        r2.left = 10;
        r2.top = 5;
        r2.right = mWidth;
        r2.bottom = mHeight;
        canvas.drawRoundRect(r2, 20, 20, paint);
//        canvas.drawRect(startX, startY, mWidth, mHeight, mPaint);
    }

    /**
     * 画出进度条
     *
     * @param canvas
     */
    private void drawProgress(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);// 设置是否抗锯齿
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);// 帮助消除锯齿
        paint.setColor(Color.parseColor(mProgressPaintColor));
        paint.setStyle(Paint.Style.FILL); //设置为实心
        paint.setStrokeWidth(5);// 设置画笔宽度
        RectF r2 = new RectF();
        r2.left = 10;
        r2.top = 5;
        r2.right = ((float) mProgress / mMaxProgress) * mWidth;
        r2.bottom = mHeight;
        canvas.drawRoundRect(r2, 20, 20, paint);
    }

    /**
     * 绘制下标进度数字
     */
    private void drawText(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);// 设置是否抗锯齿
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);// 帮助消除锯齿
        paint.setStrokeWidth(10);// 设置画笔宽度
        paint.setColor(Color.parseColor(mProgressTextColor));
        paint.setTextSize(45);
        //数值进度值
        if (mProgressType == 0) {
            canvas.drawText(mProgress + "%", ((float) mProgress / mMaxProgress) * mWidth - 45,
                    mHeight + 55, paint);
        } else {
            canvas.drawText(getProgressByTime(), ((float) mProgress / mMaxProgress) * mWidth - 45,
                    mHeight + 55, paint);
        }
    }

    private String getProgressByTime() {
        String seekToStr = "";
        ProgressCalcUtils dateTimeUtils = new ProgressCalcUtils(mProgress / 1000);
        String hours = (dateTimeUtils.getHours() <= 0 ? "" : dateTimeUtils.getHours()) + "";
        String minutes = dateTimeUtils.getMINUTES() <= 9 ? "0" + dateTimeUtils.getMINUTES() : dateTimeUtils.getMINUTES() + "";
        String seconds = dateTimeUtils.getSecond() <= 9 ? "0" + dateTimeUtils.getSecond() : dateTimeUtils.getSecond() + "";
        if (hours.equals("")) {
            seekToStr = minutes + ":" + seconds;
        } else {
            seekToStr = hours + ":" + minutes + ":" + seconds;
        }
        return seekToStr;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.mWidth = getMeasure(widthMeasureSpec) - 80;
        this.mHeight = getMeasure(heightMeasureSpec) - 90;
    }

    /**
     * 根据xml设置的宽高模式 得到实际宽，高度
     *
     * @param measureSpec
     * @return
     */
    private int getMeasure(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        int result = 500;
        if (specMode == MeasureSpec.AT_MOST) {
            result = specSize;
        } else if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        }
        return result;
    }

    public void setBackgroundPaintColor(String color) {
        mBackgroundPaintColor = color;
    }

    public void setProgressPaintColor(String color) {
        mProgressPaintColor = color;
    }

    public void setProgressTextColor(String color) {
        mProgressTextColor = color;
    }

    public void setWidth(int width) {
        mWidth = width;
        mWidth = AndroidSystemHelper.dp2px(mWidth, mContext);
    }

    public void setHeight(int height) {
        mHeight = height;
        mHeight = AndroidSystemHelper.dp2px(mHeight, mContext);
    }

    public void setMaxProgress(int mMaxProgressProgress) {
        mMaxProgress = mMaxProgressProgress;
    }

    public void setProgress(int progress) {
        mProgress = progress;
        invalidate();
    }

    /**
     * 0 数值进度值%  1 时间进度值
     *
     * @param type
     */
    public void setProgressType(int type) {
        this.mProgressType = type;
    }
}