package com.smadacm.reciperepo.model.db;

import android.content.Context;

import com.smadacm.reciperepo.db.DbContract;

public class MealType extends AbstractModel {
    String name;
    int remoteId;

    protected void setupColumns(){
        this.columns.add("name");
        this.columns.add("remoteId");
    }

    public MealType(Context context){
        super(context);
        this.tableName = DbContract.MealType.TABLE_NAME;
    }

    public MealType(Context context, String name, int remoteId){
        super(context);
        this.tableName = DbContract.MealType.TABLE_NAME;

        this.name = name;
        this.remoteId = remoteId;
    }

    public     void setName(String name) { this.name = name; }
    public   String getName() { return this.name; }

    public     void setRemoteId(int remoteId) { this.remoteId = remoteId; }
    public      int getRemoteId() { return this.remoteId; }
}
