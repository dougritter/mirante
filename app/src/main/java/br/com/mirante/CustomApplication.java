package br.com.mirante;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by douglasritter on 9/28/15.
 */
public class CustomApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, BuildConfig.PARSE_APP_ID, BuildConfig.PARSE_CLIENT_KEY);
    }


}
