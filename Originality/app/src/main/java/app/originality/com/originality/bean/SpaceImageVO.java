package app.originality.com.originality.bean;

import java.io.Serializable;


public class SpaceImageVO implements Serializable {

    private int mPosition;
    private int mLocationX;
    private int mLocationY;
    private int mWidth;
    private int mHeight;
    private String mImageUrl;

    public int getPosition() {
        return mPosition;
    }

    public void setPosition(int mPosition) {
        this.mPosition = mPosition;
    }

    public int getLocationX() {
        return mLocationX;
    }

    public void setLocationX(int mLocationX) {
        this.mLocationX = mLocationX;
    }

    public int getLocationY() {
        return mLocationY;
    }

    public void setLocationY(int mLocationY) {
        this.mLocationY = mLocationY;
    }

    public int getWidth() {
        return mWidth;
    }

    public void setWidth(int mWidth) {
        this.mWidth = mWidth;
    }

    public int getHeight() {
        return mHeight;
    }

    public void setHeight(int mHeight) {
        this.mHeight = mHeight;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }
}