package br.com.mirante;

import android.app.Application;

import com.parse.Parse;
import com.crashlytics.android.Crashlytics;
import com.parse.ParseObject;

import br.com.mirante.model.Channel;
import br.com.mirante.model.Post;
import io.fabric.sdk.android.Fabric;

/**
 * Created by douglasritter on 9/28/15.
 */
public class CustomApplication extends Application {

    @Override public void onCreate() {
        super.onCreate();
//        Fabric.with(this, new Crashlytics());

        ParseObject.registerSubclass(Channel.class);
        ParseObject.registerSubclass(Post.class);
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, BuildConfig.PARSE_APP_ID, BuildConfig.PARSE_CLIENT_KEY);

    }


}
