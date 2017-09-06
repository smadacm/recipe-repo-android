package com.smadacm.reciperepo.model.db;

import android.content.Context;

import com.smadacm.reciperepo.db.DbContract;

import java.util.HashMap;

public class RecipeStep extends AbstractModel {
    Recipe recipe;
    int recipeId;
    String instruction;
    int sort;
    int remoteId;

    protected void setupColumns(){
        this.columns.add("recipeId");
        this.columns.add("instruction");
        this.columns.add("sort");
        this.columns.add("remoteId");
    }

    public RecipeStep(Context context, String instruction, Recipe recipe, int sort, int remoteId){
        super(context);
        this.tableName = DbContract.RecipeStep.TABLE_NAME;

        this.instruction = instruction;
        this.recipe = recipe;
        this.sort = sort;
        this.remoteId = remoteId;
    }
    public RecipeStep(Context context, String instruction, Recipe recipe, int sort){
        super(context);
        this.tableName = DbContract.RecipeStep.TABLE_NAME;

        this.instruction = instruction;
        this.recipe = recipe;
        this.sort = sort;
    }
    public RecipeStep(Context context, String instruction, Recipe recipe){
        super(context);
        this.tableName = DbContract.RecipeStep.TABLE_NAME;

        this.instruction = instruction;
        this.recipe = recipe;
    }
    public RecipeStep(Context context){
        super(context);
        this.tableName = DbContract.RecipeStep.TABLE_NAME;
    }

    protected void afterLoad(){
        this.loadRecipe();
    }

    public   void setRecipe(Recipe recipe) {
        this.recipe = recipe;
        this.recipeId = recipe.getId();
    }
    public Recipe getRecipe() { return this.recipe; }

    public     void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
        this.loadRecipe();
    }
    public     void loadRecipe(){
        Recipe recipe = new Recipe(this.context);
        recipe.load(this.recipeId);
        this.recipe = recipe;
    }
    public      int getRecipeId() { return this.recipeId; }

    public     void setInstruction(String instruction) { this.instruction = instruction; }
    public   String getInstruction() { return this.instruction; }

    public     void setSort(int sort) { this.sort = sort; }
    public      int getSort() { return this.sort; }

    public     void setRemoteId(int remoteId) { this.remoteId = remoteId; }
    public      int getRemoteId() { return this.remoteId; }
}
