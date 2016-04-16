package app.originality.com.originality.modules.photo.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yalantis.flipviewpager.adapter.BaseFlipAdapter;
import com.yalantis.flipviewpager.utils.FlipSettings;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import app.originality.com.originality.R;
import app.originality.com.originality.config.Contants;
import app.originality.com.originality.modules.photo.interfaces.RecyclerViewItemClickListenner;
import app.originality.com.originality.modules.photo.model.Friend;

public class FriendsAdapter extends BaseFlipAdapter {

    private final int PAGES = 3;
    private int[] IDS_INTEREST = {R.id.interest_1, R.id.interest_2, R.id.interest_3, R.id.interest_4, R.id.interest_5};
    private RecyclerViewItemClickListenner recyclerViewItemClickListenner;

    public FriendsAdapter(Context context, List<Friend> items, FlipSettings settings) {
        super(context, items, settings);
    }

    @Override
    public View getPage(final int position, View convertView, ViewGroup parent, Object friend1, Object friend2, CloseListener closeListener) {
        final FriendsHolder holder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.friends_merge_page, parent, false);
            holder = new FriendsHolder(convertView, parent);
            convertView.setTag(holder);
        } else {
            holder = (FriendsHolder) convertView.getTag();
        }

        /**
         * 点击事件回调
         */
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerViewItemClickListenner.onRecyclerViewClick(position, v);
            }
        });

//        switch (position) {
//            // Merged page with 2 friends
//            case 1:
//                holder.leftAvatar.setImageResource(((Friend) friend1).getAvatar());
//                if (friend2 != null)
//                    holder.rightAvatar.setImageResource(((Friend) friend2).getAvatar());
//                break;
//            default:
//                fillHolder(holder, position == 0 ? (Friend) friend1 : (Friend) friend2);
//                holder.infoPage.setTag(holder);
//                return holder.infoPage;
//        }

        switch (position) {
            // Merged page with 2 friends
            case 1:
                Uri uri1 = Uri.parse(((Friend) friend1).getImgUrl());
                holder.leftAvatar.setImageURI(uri1);
                if (friend2 != null) {
                    Uri uri2 = Uri.parse(((Friend) friend2).getImgUrl());
                    holder.rightAvatar.setImageURI(uri2);
                }
                break;
            default:
                fillHolder(holder, position == 0 ? (Friend) friend1 : (Friend) friend2);
                holder.infoPage.setTag(holder);
                return holder.infoPage;
        }
        return convertView;
    }

    @Override
    public int getPagesCount() {
        return PAGES;
    }

    private void fillHolder(FriendsHolder holder, Friend friend) {
        if (friend == null)
            return;
        Iterator<TextView> iViews = holder.interests.iterator();
        Iterator<String> iInterests = friend.getInterests().iterator();
        while (iViews.hasNext() && iInterests.hasNext())
            iViews.next().setText(iInterests.next());
        holder.infoPage.setBackgroundColor(mContext.getResources().getColor(friend.getBackground()));
        holder.nickName.setText(friend.getNickname());
    }

    class FriendsHolder {
        SimpleDraweeView leftAvatar;
        SimpleDraweeView rightAvatar;
        View infoPage;

        List<TextView> interests = new ArrayList<>();
        TextView nickName;

        public FriendsHolder(View convertView, ViewGroup parent) {
            this.leftAvatar = (SimpleDraweeView) convertView.findViewById(R.id.first);
            this.rightAvatar = (SimpleDraweeView) convertView.findViewById(R.id.second);
            this.infoPage = mInflater.inflate(R.layout.friends_info, parent, false);
            this.nickName = (TextView) this.infoPage.findViewById(R.id.nickname);
            init();
        }

        public void init() {
            for (int id : IDS_INTEREST)
                this.interests.add((TextView) this.infoPage.findViewById(id));
        }
    }
}