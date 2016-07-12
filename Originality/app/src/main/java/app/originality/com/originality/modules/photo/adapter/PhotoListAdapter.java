package app.originality.com.originality.modules.photo.adapter;

import android.app.Activity;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bm.library.Info;
import com.bm.library.PhotoView;
import com.nostra13.universalimageloader.core.ImageLoader;

import app.originality.com.originality.R;
import app.originality.com.originality.adapter.OriginalityBaseAdapter;
import app.originality.com.originality.bean.SpaceImageVO;
import app.originality.com.originality.config.Contants;
import app.originality.com.originality.factory.OYEventFactory;
import app.originality.com.originality.modules.photo.bean.PhotoBeanVO;
import app.originality.com.originality.modules.photo.ui.SpaceImageDetailActivity2;
import app.originality.com.originality.util.AndroidSystemHelper;
import app.originality.com.originality.util.JumpManager;
import app.originality.com.originality.util.StringUtils;
import app.originality.com.originality.util.animation.Rotate3dAnimation;
import de.greenrobot.event.EventBus;

import java.util.List;
import java.util.Random;

public class PhotoListAdapter extends OriginalityBaseAdapter {


    public PhotoListAdapter(Activity act, List data) {
        super(act, data);
        mRandom = new Random();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mAct).inflate(R.layout.item_photo_list, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //动态随机设定 图片高度
        int positionHeight = getPositionRatio(position);
        int height = viewHolder.mImg.getLayoutParams().height;
        //对比当前图片高度 限制其不能大于200dp
        viewHolder.mImg.getLayoutParams().height = height > AndroidSystemHelper.dp2px(200, mAct) ? height : height + positionHeight;

        final PhotoBeanVO photoBeanVO = (PhotoBeanVO) mData.get(position);
        //设置不可以双指缩放移动放大等操作，跟普通的image一模一样,默认情况下就是disenable()状态
        viewHolder.mImg.disenable();
        viewHolder.mImg.setOnClickListener(new OnClick(position, viewHolder.mImg, viewHolder.mImg.getInfo(), photoBeanVO.getUrl()));

        ImageLoader.getInstance().displayImage(photoBeanVO.getUrl(), viewHolder.mImg);

        return convertView;
    }


    public class ViewHolder {
        private PhotoView mImg;

        private ViewHolder(View view) {
            this.mImg = (PhotoView) view.findViewById(R.id.id_photo_img);
        }
    }


    private final Random mRandom;

    private int getPositionRatio(int position) {
        int totalHeight = AndroidSystemHelper.dp2px(mRandom.nextInt(100 + position), mAct);
        return totalHeight;
    }


    public class OnClick implements View.OnClickListener {

        private int position;
        private PhotoView img;
        private Info info;
        private String url;

        public OnClick(int position, PhotoView img, Info info, String url) {
            this.position = position;
            this.img = img;
            this.info = info;
            this.url = url;
        }

        @Override
        public void onClick(View v) {
//            int[] location = new int[2];
//            img.getLocationOnScreen(location);
//            SpaceImageVO spaceImageVO = new SpaceImageVO();
//            spaceImageVO.setImageUrl(Contants.imageUrls[position]);
//            spaceImageVO.setPosition(position);
//            spaceImageVO.setLocationX(location[0]);
//            spaceImageVO.setLocationY(location[1]);
//            spaceImageVO.setWidth(img.getWidth());
//            spaceImageVO.setHeight(img.getHeight());
//            Info info = img.getInfo();
//            EventBus.getDefault().post(new OYEventFactory.OnItemClickTouchEvent(position, view, spaceImageVO, info));

            SpaceImageDetailActivity2.setInfo(img.getInfo(), url);
            JumpManager.jumpSpaceImageActivity2(mAct);
        }
    }

}