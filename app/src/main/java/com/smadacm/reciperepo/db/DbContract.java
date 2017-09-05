package com.smadacm.reciperepo.db;

import android.provider.BaseColumns;

public final class DbContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private DbContract() {}

    public static class DishType implements BaseColumns {
        public static final String TABLE_NAME = "dish_type";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_REMOTE_ID = "remote_id";
    }
    public static class MealType implements BaseColumns {
        public static final String TABLE_NAME = "meal_type";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_REMOTE_ID = "remote_id";
    }
    public static class Recipe implements BaseColumns {
        public static final String TABLE_NAME = "recipe";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PREP_TIME = "prep_time";
        public static final String COLUMN_COOK_TIME = "cook_time";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_DISH_TYPE_ID = "dish_type_id";
        public static final String COLUMN_MEAL_TYPE_ID = "meal_type_id";
        public static final String COLUMN_OWNER_NAME = "owner_name";
        public static final String COLUMN_REMOTE_ID = "remote_id";
    }
    public static class RecipeIngredient implements BaseColumns {
        public static final String TABLE_NAME = "recipe_ingredient";
        public static final String COLUMN_RECIPE_ID = "recipe_id";
        public static final String COLUMN_INGREDIENT = "ingredient";
        public static final String COLUMN_ORDER = "sort";
        public static final String COLUMN_REMOTE_ID = "remote_id";
    }
    public static class RecipeStep implements BaseColumns {
        public static final String TABLE_NAME = "recipe_step";
        public static final String COLUMN_RECIPE_ID = "recipe_id";
        public static final String COLUMN_INSTRUCTION = "instruction";
        public static final String COLUMN_ORDER = "sort";
        public static final String COLUMN_REMOTE_ID = "remote_id";
    }
    // TODO: To be sorted out later
    public static class RecipePicture implements BaseColumns {
        public static final String TABLE_NAME = "recipe_picture";
        public static final String COLUMN_RECIPE_ID = "recipe_id";
        public static final String COLUMN_PICTURE = "picture";
        public static final String COLUMN_REMOTE_ID = "remote_id";
    }
    // TODO: To be sorted out later
    public static class RecipeList implements BaseColumns {
        public static final String TABLE_NAME = "recipe_list";
        public static final String COLUMN_NAME = "recipe_id";
        public static final String COLUMN_REMOTE_ID = "remote_id";
    }
    // TODO: To be sorted out later
    public static class RecipeListMember implements BaseColumns {
        public static final String TABLE_NAME = "recipe_list_member";
        public static final String COLUMN_LIST_ID = "list_id";
        public static final String COLUMN_RECIPE_ID = "recipe_id";
        public static final String COLUMN_REMOTE_ID = "remote_id";
    }

    public static final String SQL_CREATE_DISH_TYPE =
            "CREATE TABLE IF NOT EXISTS " + DishType.TABLE_NAME + " (" +
                    DishType._ID + " INTEGER PRIMARY KEY," +
                    DishType.COLUMN_NAME + " TEXT," +
                    DishType.COLUMN_REMOTE_ID + " TEXT UNIQUE)";
    public static final String SQL_CREATE_MEAL_TYPE =
            "CREATE TABLE IF NOT EXISTS " + MealType.TABLE_NAME + " (" +
                    MealType._ID + " INTEGER PRIMARY KEY," +
                    MealType.COLUMN_NAME + " TEXT," +
                    MealType.COLUMN_REMOTE_ID + " TEXT UNIQUE)";
    public static final String SQL_CREATE_RECIPE =
            "CREATE TABLE IF NOT EXISTS " + Recipe.TABLE_NAME + " (" +
                    Recipe._ID + " INTEGER PRIMARY KEY," +
                    Recipe.COLUMN_NAME + " TEXT," +
                    Recipe.COLUMN_PREP_TIME + " INTEGER," +
                    Recipe.COLUMN_COOK_TIME + " INTEGER," +
                    Recipe.COLUMN_DESCRIPTION + " TEXT," +
                    Recipe.COLUMN_DISH_TYPE_ID + " INTEGER," +
                    Recipe.COLUMN_MEAL_TYPE_ID + " INTEGER," +
                    Recipe.COLUMN_OWNER_NAME + " TEXT," +
                    Recipe.COLUMN_REMOTE_ID + " INTEGER UNIQUE" +
                    ")";
    public static final String SQL_CREATE_RECIPE_INGREDIENT =
            "CREATE TABLE IF NOT EXISTS " + RecipeIngredient.TABLE_NAME + " (" +
                    RecipeIngredient._ID + " INTEGER PRIMARY KEY," +
                    RecipeIngredient.COLUMN_RECIPE_ID + " INTEGER," +
                    RecipeIngredient.COLUMN_INGREDIENT + " TEXT," +
                    RecipeIngredient.COLUMN_ORDER + " INTEGER," +
                    RecipeIngredient.COLUMN_REMOTE_ID + " INTEGER UNIQUE" +
                    ")";
    public static final String SQL_CREATE_RECIPE_STEP =
            "CREATE TABLE IF NOT EXISTS " + RecipeStep.TABLE_NAME + " (" +
                    RecipeStep._ID + " INTEGER PRIMARY KEY," +
                    RecipeStep.COLUMN_INSTRUCTION + " TEXT," +
                    RecipeStep.COLUMN_RECIPE_ID + " INTEGER," +
                    RecipeStep.COLUMN_ORDER + " INTEGER," +
                    RecipeStep.COLUMN_REMOTE_ID + " INTEGER UNIQUE" +
                    ")";

    public static final String SQL_DROP_DISH_TYPE =
            "DROP TABLE IF EXISTS " + DishType.TABLE_NAME;
    public static final String SQL_DROP_MEAL_TYPE =
            "DROP TABLE IF EXISTS " + MealType.TABLE_NAME;
    public static final String SQL_DROP_RECIPE =
            "DROP TABLE IF EXISTS " + Recipe.TABLE_NAME;
    public static final String SQL_DROP_RECIPE_INGREDIENT =
            "DROP TABLE IF EXISTS " + RecipeIngredient.TABLE_NAME;
    public static final String SQL_DROP_RECIPE_STEP =
            "DROP TABLE IF EXISTS " + RecipeStep.TABLE_NAME;
    public static final String SQL_DROP_RECIPE_PICTURE =
            "DROP TABLE IF EXISTS " + RecipePicture.TABLE_NAME;
    public static final String SQL_DROP_LIST =
            "DROP TABLE IF EXISTS " + RecipeList.TABLE_NAME;
    public static final String SQL_DROP_RECIPE_LIST_MEMBER =
            "DROP TABLE IF EXISTS " + RecipeListMember.TABLE_NAME;
}
