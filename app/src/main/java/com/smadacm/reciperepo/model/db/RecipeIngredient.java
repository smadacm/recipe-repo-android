package com.smadacm.reciperepo.model.db;

public class RecipeIngredient {
    Recipe recipe;
    String ingredient;
    int order;
    int remoteId;

    public RecipeIngredient(String ingredient, Recipe recipe, int order, int remoteId){
        this.ingredient = ingredient;
        this.recipe = recipe;
        this.order = order;
        this.remoteId = remoteId;
    }
    public RecipeIngredient(String ingredient, Recipe recipe, int order){
        this.ingredient = ingredient;
        this.recipe = recipe;
        this.order = order;
    }
    public RecipeIngredient(String ingredient, Recipe recipe){
        this.ingredient = ingredient;
        this.recipe = recipe;
    }
}
