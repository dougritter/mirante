package br.com.mirante.view.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

import br.com.mirante.R;
import br.com.mirante.model.Channel;
import br.com.mirante.util.Constants;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {

    // Constants
    public static final String LOG_TAG = HomeActivity.class.getSimpleName();
    public static final int ACCOUNT_CREATED = 101;

    // View Objects
    @Bind(R.id.user) EditText user;
    @Bind(R.id.password) EditText password;

    // Java objects
    private Channel mSelectedChannel;
    private List<Channel> mRetrievedChannels;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (ParseUser.getCurrentUser() != null) {
            getSelectedChannel();

            Log.e(LOG_TAG, "User is already logged in: " + ParseUser.getCurrentUser().get(Constants.PARSE_USER_ATTR_NAME));
//            showFeed();
        } else {
            retrieveChannels();
            Log.e(LOG_TAG, "User not logged in");

        }

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

                if (list.size() == 0) {
                    retrieveChannels();

                } else {
                    mSelectedChannel = (Channel) list.get(0);
                    Log.e(LOG_TAG, "Found channel selected: " + mSelectedChannel.toString());
                }

            }
        });

    }

    public void retrieveChannels(){
        ParseQuery query = new ParseQuery("Channel");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> channels, ParseException e) {
                if (e == null) {
                    Log.e(LOG_TAG, "Retrieved channels. Size: "+channels.size());

                    if (channels.size() > 0) {
                        mRetrievedChannels = (List) channels;
                    }

                } else {
                    Log.e(LOG_TAG, "Error: " + e.getMessage());
                }
            }
        });

    }

    @OnClick(R.id.submitButton) void login(View view) {
        if (!user.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
            ParseUser.logInInBackground(user.getText().toString().trim(),
                    password.getText().toString().trim(), new LogInCallback() {
                        public void done(ParseUser user, ParseException e) {
                            if (user != null) {
                                Log.e(LOG_TAG, "User was logged in - " + user.getEmail());
                                showFeed();
                            } else {
                                Log.e(LOG_TAG, "Sign in failed - " + e.getMessage());
                            }
                        }
                    });

        }

    }

    public void showFeed() {
        if (mSelectedChannel == null && mRetrievedChannels != null) {
            showChannelsList(mRetrievedChannels);
        } else {
            Intent intent = new Intent(HomeActivity.this, FeedActivity.class);
            startActivity(intent);
            finish();

        }

    }


    @OnClick(R.id.createAccountButton) void createNewAccount(View view) {
        Intent intent = new Intent(HomeActivity.this, NewAccountActivity.class);
        startActivityForResult(intent, ACCOUNT_CREATED);

    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ACCOUNT_CREATED) {
            Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Login not successful", Toast.LENGTH_SHORT).show();

        }

    }

    public void showChannelsList(List<Channel> channelList) {
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>( this, android.R.layout.select_dialog_multichoice);

        for(int i=0; i<channelList.size(); i++){
            arrayAdapter.add(channelList.get(i).getName());
        }

        final AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle(R.string.channels_list_dialog_title)
                .setAdapter(arrayAdapter, null)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialogInterface, int i) {
                        if (mSelectedChannel != null) {
                            showFeed();
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    getString(R.string.message_need_to_choose_a_channel), Toast.LENGTH_SHORT).show();
                        }

                    }
                })
                .setNegativeButton(getResources().getString(android.R.string.cancel), null)
                .create();

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override public void onDismiss(DialogInterface dialogInterface) {}
        });

        dialog.getListView().setItemsCanFocus(false);
//        dialog.getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        dialog.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Log.e(LOG_TAG, "list is not empty - saving the last item in the localDataStore");
                mSelectedChannel = mRetrievedChannels.get(position);

                mSelectedChannel.pinInBackground(new SaveCallback() {
                    @Override public void done(ParseException e) {
                        Log.e(LOG_TAG, "channel "+ mSelectedChannel.toString() + "saved in the localDataStore");
                    }
                });

                dialog.dismiss();
            }
        });

        dialog.show();

    }
}
