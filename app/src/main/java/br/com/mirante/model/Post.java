package br.com.mirante.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.io.Serializable;

import br.com.mirante.utils.Constants;

/**
 * Created by douglas.machado on 12/14/15.
 */
@ParseClassName(Constants.PARSE_CLASS_POST)
public class Post extends ParseObject {

    public Channel getChannel() {
        return (Channel) getParseObject(Constants.PARSE_ATTR_CHANNEL);
    }

    public void setChannel(Channel channel) {
        put(Constants.PARSE_ATTR_CHANNEL, channel);
    }

    public String getTitle() {
        return getString(Constants.PARSE_ATTR_POST_TITLE);
    }

    public void setTitle(String title) {
        put(Constants.PARSE_ATTR_POST_TITLE, title);
    }

    public String getText() {
        return getString(Constants.PARSE_ATTR_POST_TEXT);
    }

    public void setText(String text) {
        put(Constants.PARSE_ATTR_POST_TEXT, text);
    }
}
