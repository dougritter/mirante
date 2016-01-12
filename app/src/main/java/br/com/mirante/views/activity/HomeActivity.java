package br.com.mirante.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import br.com.mirante.R;
import br.com.mirante.utils.Constants;
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

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (ParseUser.getCurrentUser() != null) {
            Log.e(LOG_TAG, "User is already logged in: " + ParseUser.getCurrentUser().get(Constants.PARSE_USER_ATTR_NAME));
            showFeed();
        } else {
            Log.e(LOG_TAG, "User not logged in");

        }

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
        Intent intent = new Intent(HomeActivity.this, FeedActivity.class);
        startActivity(intent);
        finish();
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
}
