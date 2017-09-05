package com.smadacm.reciperepo.model.db;

import android.content.Context;

import com.smadacm.reciperepo.db.DbContract;

import java.util.HashMap;

public class RecipeStep {
    Recipe recipe;
    String instruction;
    int order;
    int remoteId;

    public RecipeStep(String instruction, Recipe recipe, int order, int remoteId){
        this.instruction = instruction;
        this.recipe = recipe;
        this.order = order;
        this.remoteId = remoteId;
    }
    public RecipeStep(String instruction, Recipe recipe, int order){
        this.instruction = instruction;
        this.recipe = recipe;
        this.order = order;
    }
    public RecipeStep(String instruction, Recipe recipe){
        this.instruction = instruction;
        this.recipe = recipe;
    }
}
