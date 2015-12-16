package br.com.mirante.views.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import br.com.mirante.R;
import br.com.mirante.utils.Constants;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewAccountActivity extends AppCompatActivity {

    // Constants
    public static final String LOG_TAG = NewAccountActivity.class.getSimpleName();

    // View Objects
    @Bind(R.id.newName) EditText newName;
    @Bind(R.id.newUser) EditText newUser;
    @Bind(R.id.newPassword) EditText newPassword;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @OnClick(R.id.saveNewAccount)
    void createAccount(View view) {
        ParseUser parseUser = new ParseUser();
        parseUser.setUsername(newUser.getText().toString());
        parseUser.setEmail(newUser.getText().toString());
        parseUser.setPassword(newPassword.getText().toString());
        parseUser.put(Constants.PARSE_USER_ATTR_NAME, newName.getText().toString().trim());

        parseUser.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    // Hooray! Let them use the app now.
                    Log.e(LOG_TAG, "Hooray! Let them use the app now");
                    Toast.makeText(getApplicationContext(), "Hooray! Let them use the app now", Toast.LENGTH_SHORT).show();

                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                    Log.e(LOG_TAG, "Sign up didn't succeed. Look at the ParseException");
                    Toast.makeText(getApplicationContext(), "Sign up didn't succeed. Look at the ParseException", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
