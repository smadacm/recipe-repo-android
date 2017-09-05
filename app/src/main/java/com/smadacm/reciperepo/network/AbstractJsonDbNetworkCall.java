package com.smadacm.reciperepo.network;

import android.content.Context;

import com.smadacm.reciperepo.db.DbHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class AbstractJsonDbNetworkCall extends AbstractDbDependentNetworkCall {
    protected DbHelper dbHelper;
    protected Context context;

    public AbstractJsonDbNetworkCall(Context ctx){
        super(ctx);
    }

    public JSONObject preAfterAsync(String result){
        JSONObject dump;
        try {
            dump = new JSONObject(result);
        } catch(JSONException e){
            // log unable to parse
            return null;
        }
        return dump;
    }
}
