package app.originality.com.originality.modules.photo.ui;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.yalantis.flipviewpager.adapter.BaseFlipAdapter;
import com.yalantis.flipviewpager.utils.FlipSettings;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;


import app.originality.com.originality.R;
import app.originality.com.originality.modules.photo.adapter.FriendsAdapter;
import app.originality.com.originality.modules.photo.model.Friend;
import app.originality.com.originality.modules.photo.utils.Utils;

public class AlbumListActivity extends Activity {

    private RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_list);
//        final ListView friends = (ListView) findViewById(R.id.friends);
        mRecyclerView = (RecyclerView) this.findViewById(R.id.id_recyclerview);

        FlipSettings settings = new FlipSettings.Builder().defaultPage(1).build();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(new FriendsAdapter(AlbumListActivity.this, Utils.friends, settings));

    }
}
