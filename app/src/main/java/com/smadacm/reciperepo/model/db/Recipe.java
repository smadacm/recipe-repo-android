package com.smadacm.reciperepo.model.db;

import android.content.Context;

import com.smadacm.reciperepo.db.DbContract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Recipe extends AbstractModel {
    String name;
    int prepTime;
    int cookTime;
    String description;
    DishType dishType;
    int dishTypeId;
    MealType mealType;
    int mealTypeId;
    String ownerName;
    int remoteId;

    protected void setupColumns(){
        this.columns.add("name");
        this.columns.add("prepTime");
        this.columns.add("cookTime");
        this.columns.add("description");
        this.columns.add("dishTypeId");
        this.columns.add("mealTypeId");
        this.columns.add("ownerName");
        this.columns.add("remoteId");
    }

    public Recipe(Context context){
        super(context);
        this.tableName = DbContract.Recipe.TABLE_NAME;
    }

    public Recipe(Context context, String name, int prepTime, int cookTime, String description,
                  DishType dishType, MealType mealType, String ownerName, int remoteId){
        super(context);
        this.tableName = DbContract.Recipe.TABLE_NAME;

        this.name = name;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.description = description;
        this.dishType = dishType;
        this.mealType = mealType;
        this.ownerName = ownerName;
        this.remoteId = remoteId;
    }
    public Recipe(Context context, String name, int prepTime, int cookTime, String description,
                  DishType dishType, MealType mealType, String ownerName){
        super(context);
        this.tableName = DbContract.Recipe.TABLE_NAME;

        this.name = name;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.description = description;
        this.dishType = dishType;
        this.mealType = mealType;
        this.ownerName = ownerName;
    }
    public Recipe(Context context, String name, int prepTime, int cookTime, String description,
                  DishType dishType, MealType mealType){
        super(context);
        this.tableName = DbContract.Recipe.TABLE_NAME;

        this.name = name;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.description = description;
        this.dishType = dishType;
        this.mealType = mealType;
    }

    protected void afterLoad(){
        this.loadDishType();
        this.loadMealType();
    }

    public     void setName(String name) { this.name = name; }
    public   String getName() { return this.name; }

    public     void setPrepTime(int prepTime) { this.prepTime = prepTime; }
    public      int getPrepTime() { return this.prepTime; }

    public     void setCookTime(int cookTime) { this.cookTime = cookTime; }
    public      int getCookTime() { return this.cookTime; }

    public     void setDescription(String description) { this.description = description; }
    public   String getDescription() { return this.description; }

    public     void setDishType(DishType dishType) {
        this.dishType = dishType;
        this.dishTypeId = dishType.getId();
    }
    public DishType getDishType() { return this.dishType; }

    public     void setDishTypeId(int dishTypeId){
        this.dishTypeId = dishTypeId;
        this.loadDishType();
    }
    public     void loadDishType() {
        DishType dishType = new DishType(this.context);
        dishType.load(this.dishTypeId);
        this.dishType = dishType;
    }
    public      int getDishTypeId() { return this.dishTypeId; }

    public     void setMealType(MealType mealType) {
        this.mealType = mealType;
        this.mealTypeId = mealType.getId();
    }
    public MealType getMealType() { return this.mealType; }

    public     void setMealTypeId(int mealTypeId){
        this.mealTypeId = mealTypeId;
        this.loadMealType();
    }
    public     void loadMealType() {
        MealType mealType = new MealType(this.context);
        mealType.load(this.mealTypeId);
        this.mealType = mealType;
    }
    public      int getMealTypeId() { return this.mealTypeId; }

    public     void setOwnerName(String ownerName) { this.ownerName = ownerName; }
    public   String getOwnerName() { return this.ownerName; }

    public     void setRemoteId(int remoteId) { this.remoteId = remoteId; }
    public      int getRemoteId() { return this.remoteId; }

    public List<RecipeIngredient> getIngredients(){
        List<RecipeIngredient> ingredients = new ArrayList<>();
        HashMap<String, String> wheres = new HashMap<>();
        wheres.put("recipe_id", Integer.valueOf(this.getId()).toString());
        List<AbstractModel> ingredientsRaw = AbstractModel.loadMany(this.context, RecipeIngredient.class, wheres);
        for(int i=0; i < ingredientsRaw.size(); i++){
            ingredients.add((RecipeIngredient) ingredientsRaw.get(i));
        }
        return ingredients;
    }
    public List<RecipeStep> getSteps(){
        List<RecipeStep> steps = new ArrayList<>();
        HashMap<String, String> wheres = new HashMap<>();
        wheres.put("recipe_id", Integer.valueOf(this.getId()).toString());
        List<AbstractModel> stepsRaw = AbstractModel.loadMany(this.context, RecipeStep.class, wheres);
        for(int i=0; i < stepsRaw.size(); i++){
            steps.add((RecipeStep) stepsRaw.get(i));
        }
        return steps;
    }
}
