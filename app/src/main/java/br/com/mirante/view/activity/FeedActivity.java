package br.com.mirante.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import br.com.mirante.R;
import br.com.mirante.databinding.ActivityFeedBinding;
import br.com.mirante.model.Channel;
import br.com.mirante.model.Post;
import br.com.mirante.util.Constants;
import br.com.mirante.view.adapter.FeedAdapter;

public class FeedActivity extends AppCompatActivity {

    private static final String LOG_TAG = FeedActivity.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private FeedAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ActivityFeedBinding mBinding;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_feed);

        Toolbar toolbar = mBinding.toolbar;
        setSupportActionBar(toolbar);

        mRecyclerView = mBinding.contentFeed.myRecyclerView;
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new FeedAdapter(new ArrayList<Post>());
        mRecyclerView.setAdapter(mAdapter);

        getSelectedChannel();

    }

    public void getSelectedChannel() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(Constants.PARSE_CLASS_CHANNEL);
        query.fromLocalDatastore();
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    Log.e(LOG_TAG, "Channels retrieved from localDataStore - list size: " + list.size());
                } else {
                    Log.e(LOG_TAG, "Error on retrieving Channels from localDataStore");
                }

                if (list.size() > 0) {
                    Channel channel = (Channel) list.get(0);
                    Log.e(LOG_TAG, "Found channel selected: " + channel.toString());
                    getFeed(channel);

                } else {
                    Log.e(LOG_TAG, "Not found a channel selected");
                }

            }
        });

    }



    public void getFeed(Channel channel) {
        ParseQuery query = new ParseQuery(Constants.PARSE_CLASS_POST);
        query.whereEqualTo(Constants.PARSE_ATTR_CHANNEL, channel);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> posts, ParseException e) {
                if (e == null) {
                    Log.e(LOG_TAG, "Retrieved posts. Size: "+posts.size());

                    if (posts.size() > 0) {
                        @SuppressWarnings("unchecked")
                        List<Post> postList = (List) posts;
                        mAdapter.setDataset(postList);

                    }
                } else {
                    Log.e(LOG_TAG, "Error: " + e.getMessage());
                }
            }
        });

    }


}
