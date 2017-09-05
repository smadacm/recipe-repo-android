package com.smadacm.reciperepo.network;

import android.content.Context;

import com.smadacm.reciperepo.db.DbHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class AbstractDbDependentNetworkCall extends AbstractNetworkCall {
    protected DbHelper dbHelper;
    protected Context context;

    public AbstractDbDependentNetworkCall(Context ctx){
        super();
        this.context = context;
        this.dbHelper = new DbHelper(ctx);
    }
}
