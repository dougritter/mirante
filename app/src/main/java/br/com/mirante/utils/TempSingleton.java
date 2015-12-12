package br.com.mirante.utils;

import com.parse.ParseObject;

/**
 * Created by douglasritter on 12/12/15.
 */
public class TempSingleton {
    private static TempSingleton ourInstance = new TempSingleton();
    public static TempSingleton getInstance() {
        return ourInstance;
    }

    private ParseObject channel;

    private TempSingleton() {
    }

    public ParseObject getChannel() {
        return channel;
    }

    public void setChannel(ParseObject channel) {
        this.channel = channel;
    }
}
