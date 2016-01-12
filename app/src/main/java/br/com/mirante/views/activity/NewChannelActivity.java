package br.com.mirante.views.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import br.com.mirante.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewChannelActivity extends AppCompatActivity {

    // Constants
    public static final String LOG_TAG = NewChannelActivity.class.getSimpleName();

    // Create channel
    @Bind(R.id.channel) EditText channel;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_channel);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }


    @OnClick(R.id.createChannelButton) void createChannel(View view){
        String CHANNEL = "Channel";
        String CHANNEL_NAME = "channel_name";

        ParseObject parseObject = new ParseObject(CHANNEL);
        parseObject.put(CHANNEL_NAME, channel.getText().toString());

        parseObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.e(LOG_TAG, "Channel " + channel.getText().toString() + " saved");
                    Toast.makeText(getApplicationContext(), "Channel " + channel.getText().toString()
                            + " saved", Toast.LENGTH_SHORT).show();

//                    retrieveChannels();

                } else {
                    Log.e(LOG_TAG, "Error: something went wrong saving Channel " + channel.getText().toString());
                    Toast.makeText(getApplicationContext(), "Error: something went wrong saving Channel "
                            + channel.getText().toString(), Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}
