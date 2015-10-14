package br.com.mirante;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Bind(R.id.personName) TextView personName;
    @Bind(R.id.email) TextView email;
    @Bind(R.id.password) TextView password;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    /*public void testParse(){
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "barr");
        testObject.saveInBackground();

    }*/

    @OnClick(R.id.createAccountButton) void createUser(View view){
        ParseUser parseUser = new ParseUser();
        parseUser.setUsername(personName.getText().toString());
        parseUser.setEmail(email.getText().toString());
        parseUser.setPassword(password.getText().toString());

        parseUser.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    // Hooray! Let them use the app now.
                    Log.e(LOG_TAG, "Hooray! Let them use the app now");

                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                    Log.e(LOG_TAG, "Sign up didn't succeed. Look at the ParseException");
                }
            }
        });


    }




}
