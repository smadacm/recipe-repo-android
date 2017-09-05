package com.smadacm.reciperepo.model.db;

public class Recipe {
    String name;
    int prepTime;
    int cookTime;
    String description;
    DishType dishType;
    MealType mealType;
    String ownerName;
    int remoteId;

    public Recipe(String name, int prepTime, int cookTime, String description,
                  DishType dishType, MealType mealType, String ownerName, int remoteId){
        this.name = name;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.description = description;
        this.dishType = dishType;
        this.mealType = mealType;
        this.ownerName = ownerName;
        this.remoteId = remoteId;
    }
    public Recipe(String name, int prepTime, int cookTime, String description,
                  DishType dishType, MealType mealType, String ownerName){
        this.name = name;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.description = description;
        this.dishType = dishType;
        this.mealType = mealType;
        this.ownerName = ownerName;
    }
    public Recipe(String name, int prepTime, int cookTime, String description,
                  DishType dishType, MealType mealType){
        this.name = name;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.description = description;
        this.dishType = dishType;
        this.mealType = mealType;
    }
}
