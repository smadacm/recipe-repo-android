package com.smadacm.reciperepo.model.db;

public class MealType {
    String name;
    int remoteId;

    public MealType(){
    }

    public MealType(String name, int remoteId){
        this.name = name;
        this.remoteId = remoteId;
    }
}
