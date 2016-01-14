package br.com.mirante.views.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

import br.com.mirante.R;
import br.com.mirante.databinding.ActivityFeedBinding;
import br.com.mirante.model.Channel;
import br.com.mirante.utils.Constants;
import br.com.mirante.views.adapter.FeedAdapter;

public class FeedActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
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
        mAdapter = new FeedAdapter(new String[]{"test", "tested"});
        mRecyclerView.setAdapter(mAdapter);

    }

    public void getFeed() {

    }

    public void retrieveChannels(){/*
        ParseQuery query = new ParseQuery(Constants.PARSE_CLASS_POST);
//        query.whereEqualTo("Brand", "Burnettes");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> channels, ParseException e) {
                if (e == null) {
                    retrievedChannels.setText("");

                    Log.e(LOG_TAG, "Retrieved channels. Size: "+channels.size());

                    for(int i=0; i<channels.size(); i++){
                        ParseObject object = channels.get(i);
                        retrievedChannels.setText(retrievedChannels.getText() + "\n"+object.getString(Constants.PARSE_ATTR_CHANNEL_NAME));
                    }

                    if (channels.size() > 0) {
                        Intent intent = new Intent(MainActivity.this, NewPostActivity.class);

                        Channel channel = (Channel) channels.get(0);
                        intent.putExtra(Constants.PARSE_ATTR_CHANNEL_OBJECT_ID, channel.getObjectId());
                        intent.putExtra(Constants.PARSE_ATTR_CHANNEL_NAME, channel.getName());


                        startActivity(intent);
                    }


                } else {
                    Log.e(LOG_TAG, "Error: " + e.getMessage());
                }
            }
        });



*/
    }




}
