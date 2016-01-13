package br.com.mirante.views.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import br.com.mirante.R;
import br.com.mirante.databinding.ActivityFeedBinding;
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


}
