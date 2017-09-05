package com.smadacm.reciperepo.model.db;

import android.content.Context;

import java.util.ArrayList;

public class DishType extends AbstractModel {
    String tableName = "dish_type";
    String name;
    int remoteId;

    protected void setupColumns(){
        this.columns.add("name");
        this.columns.add("remote_id");
    }

    public DishType(Context context){
        super(context);
    }

    public DishType(Context context, int id, String name, int remoteId){
        super(context);
        this._id = id;
        this.name = name;
        this.remoteId = remoteId;
    }
}
