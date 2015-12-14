package br.com.mirante.utils;

import com.parse.ParseObject;

import br.com.mirante.model.Channel;

/**
 * Created by douglasritter on 12/12/15.
 */
public class TempSingleton {
    private static TempSingleton ourInstance = new TempSingleton();
    public static TempSingleton getInstance() {
        return ourInstance;
    }

    private Channel channel;

    private TempSingleton() {
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
