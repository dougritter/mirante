package br.com.mirante.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.io.Serializable;

import br.com.mirante.utils.Constants;

/**
 * Created by douglas.machado on 12/14/15.
 */
@ParseClassName(Constants.PARSE_CLASS_CHANNEL)
public class Channel extends ParseObject {

    public String getName() {
        return getString(Constants.PARSE_ATTR_CHANNEL_NAME);
    }
    public void setName(String name) {
        put(Constants.PARSE_ATTR_CHANNEL_NAME, name);
    }

}
