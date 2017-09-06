package com.smadacm.reciperepo.model.db;

import android.content.Context;

import com.smadacm.reciperepo.db.DbContract;

public class RecipeIngredient extends AbstractModel {
    Recipe recipe;
    int recipeId;
    String ingredient;
    int sort;
    int remoteId;

    protected void setupColumns(){
        this.columns.add("recipeId");
        this.columns.add("ingredient");
        this.columns.add("sort");
        this.columns.add("remoteId");
    }

    public RecipeIngredient(Context context, String ingredient, Recipe recipe, int sort, int remoteId){
        super(context);
        this.tableName = DbContract.RecipeIngredient.TABLE_NAME;

        this.ingredient = ingredient;
        this.recipe = recipe;
        this.sort = sort;
        this.remoteId = remoteId;
    }
    public RecipeIngredient(Context context, String ingredient, Recipe recipe, int sort){
        super(context);
        this.tableName = DbContract.RecipeIngredient.TABLE_NAME;

        this.ingredient = ingredient;
        this.recipe = recipe;
        this.sort = sort;
    }
    public RecipeIngredient(Context context, String ingredient, Recipe recipe){
        super(context);
        this.tableName = DbContract.RecipeIngredient.TABLE_NAME;

        this.ingredient = ingredient;
        this.recipe = recipe;
    }

    public RecipeIngredient(Context context){
        super(context);
        this.tableName = DbContract.RecipeIngredient.TABLE_NAME;

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

    public     void setIngredient(String ingredient) { this.ingredient = ingredient; }
    public   String getIngredient() { return this.ingredient; }

    public     void setSort(int sort) { this.sort = sort; }
    public      int getSort() { return this.sort; }

    public     void setRemoteId(int remoteId) { this.remoteId = remoteId; }
    public      int getRemoteId() { return this.remoteId; }
}
