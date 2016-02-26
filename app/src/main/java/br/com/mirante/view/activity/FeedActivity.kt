package br.com.mirante.view.activity

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log

import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseObject
import com.parse.ParseQuery

import java.util.ArrayList

import br.com.mirante.R
import br.com.mirante.databinding.ActivityFeedBinding
import br.com.mirante.model.Channel
import br.com.mirante.model.Post
import br.com.mirante.util.Constants
import br.com.mirante.view.adapter.FeedAdapter

class FeedActivity : AppCompatActivity() {
    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: FeedAdapter? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null
    private var mBinding: ActivityFeedBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView<ActivityFeedBinding>(this, R.layout.activity_feed)

        val toolbar = mBinding!!.toolbar
        setSupportActionBar(toolbar)

        mRecyclerView = mBinding!!.contentFeed.myRecyclerView
        mRecyclerView!!.setHasFixedSize(true)
        mLayoutManager = LinearLayoutManager(this)
        mRecyclerView!!.layoutManager = mLayoutManager
        mAdapter = FeedAdapter(ArrayList<Post>())
        mRecyclerView!!.adapter = mAdapter

        getSelectedChannel()

    }

    fun getSelectedChannel() {
        val query = ParseQuery.getQuery<ParseObject>(Constants.PARSE_CLASS_CHANNEL)
        query.fromLocalDatastore()
        query.findInBackground { list, e ->
            if (e == null) {
                Log.e(LOG_TAG, "Channels retrieved from localDataStore - list size: " + list.size)
            } else {
                Log.e(LOG_TAG, "Error on retrieving Channels from localDataStore")
            }

            if (list.size > 0) {
                val channel = list[0] as Channel
                Log.e(LOG_TAG, "Found channel selected: " + channel.toString())
                getFeed(channel)

            } else {
                Log.e(LOG_TAG, "Not found a channel selected")
            }
        }

    }


    fun getFeed(channel: Channel) {
        val query = ParseQuery(Constants.PARSE_CLASS_POST)
        query.whereEqualTo(Constants.PARSE_ATTR_CHANNEL, channel)
        query.findInBackground(FindCallback<com.parse.ParseObject> { posts, e ->
            if (e == null) {
                Log.e(LOG_TAG, "Retrieved posts. Size: " + posts.size)

                if (posts.size > 0) {
                    mAdapter!!.setDataset(posts)

                }
            } else {
                Log.e(LOG_TAG, "Error: " + e.message)
            }
        })

    }

    companion object {

        val LOG_TAG = FeedActivity::class.java.simpleName
    }


}
