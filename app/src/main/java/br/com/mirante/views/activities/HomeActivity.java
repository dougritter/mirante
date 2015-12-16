package br.com.mirante.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

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
            Log.e(LOG_TAG, "User logged in: " + ParseUser.getCurrentUser().get(Constants.PARSE_USER_ATTR_NAME));
        } else {
            Log.e(LOG_TAG, "User not logged in");

        }

    }

    @OnClick(R.id.submitButton)
    void login(View view) {
        if (!user.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
            ParseUser.logInInBackground(user.getText().toString().trim(),
                    password.getText().toString().trim(), new LogInCallback() {
                public void done(ParseUser user, ParseException e) {
                    if (user != null) {
                        Log.e(LOG_TAG, "User is logged in - "+user.getEmail());
                    } else {
                        Log.e(LOG_TAG, "Sign in failed - " + e.getMessage());
                    }
                }
            });

        }


    }


    @OnClick(R.id.createAccountButton)
    void createNewAccount(View view) {
        Intent intent = new Intent(HomeActivity.this, NewAccountActivity.class);
        startActivity(intent);

    }



}
