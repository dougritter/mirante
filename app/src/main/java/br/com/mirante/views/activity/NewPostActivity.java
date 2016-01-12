package br.com.mirante.views.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.parse.ParseException;
import com.parse.SaveCallback;

import br.com.mirante.R;
import br.com.mirante.model.Channel;
import br.com.mirante.model.Post;
import br.com.mirante.utils.Constants;

public class NewPostActivity extends AppCompatActivity {

    // Constants
    public static final String LOG_TAG = NewPostActivity.class.getSimpleName();

    // Java Objects
    private Channel mChannel;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getIntent() != null && getIntent().hasExtra(Constants.PARSE_ATTR_CHANNEL_NAME)) {
            mChannel = new Channel();
            mChannel.setName(getIntent().getStringExtra(Constants.PARSE_ATTR_CHANNEL_NAME));
            mChannel.setObjectId(getIntent().getStringExtra(Constants.PARSE_ATTR_CHANNEL_OBJECT_ID));

        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(mOnclickListener);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        testCreatePost();
    }

    View.OnClickListener mOnclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    };


    public void testCreatePost() {
        if (mChannel != null) {
            Post parseObject = new Post();
            parseObject.put(Constants.PARSE_ATTR_POST_TITLE, "title test doug");
            parseObject.put(Constants.PARSE_ATTR_POST_TEXT, "text text text doug");
            parseObject.put(Constants.PARSE_ATTR_CHANNEL, mChannel);

            parseObject.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        /*Log.e(LOG_TAG, "Channel " + channel.getText().toString() + " saved");
                        Toast.makeText(getApplicationContext(), "Channel " + channel.getText().toString()
                                + " saved", Toast.LENGTH_SHORT).show();

                        retrieveChannels();*/

                    } else {
                        /*Log.e(LOG_TAG, "Error: something went wrong saving Channel " + channel.getText().toString());
                        Toast.makeText(getApplicationContext(), "Error: something went wrong saving Channel "
                                + channel.getText().toString(), Toast.LENGTH_SHORT).show();*/

                    }
                }
            });
        }

    }

















}
