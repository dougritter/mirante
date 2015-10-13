package br.com.mirante;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.ParseObject;

public class MainActivity extends AppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testParse();
    }

    public void testParse(){
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "barr");
        testObject.saveInBackground();

    }


}
