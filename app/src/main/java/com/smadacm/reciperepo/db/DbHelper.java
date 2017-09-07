package com.smadacm.reciperepo.db;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.JsonReader;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import static com.smadacm.reciperepo.db.DbContract.*;

public class DbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "RecipeRepo.db3";

    private boolean populateRunning = false;

    private SQLiteDatabase db;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        db = context.openOrCreateDatabase(DATABASE_NAME, 0, null);

//        if(!this.isReady()){
//            this.onCreate(db);
//        }
//        this.populateDb();
    }
    public void onCreate(SQLiteDatabase db) {
        this.populateDb();
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    protected void dropAllTables(){
        db.execSQL(SQL_DROP_DISH_TYPE);
        db.execSQL(SQL_DROP_MEAL_TYPE);
        db.execSQL(SQL_DROP_RECIPE);
        db.execSQL(SQL_DROP_RECIPE_INGREDIENT);
        db.execSQL(SQL_DROP_RECIPE_STEP);
        db.execSQL(SQL_DROP_RECIPE_PICTURE);
        db.execSQL(SQL_DROP_LIST);
        db.execSQL(SQL_DROP_RECIPE_LIST_MEMBER);
    }
    protected void createAllTables(){
        Log.w("RecipeRepo: DbHelper", "Creating tables");
        db.execSQL(SQL_CREATE_DISH_TYPE);
        db.execSQL(SQL_CREATE_MEAL_TYPE);
        db.execSQL(SQL_CREATE_RECIPE);
        db.execSQL(SQL_CREATE_RECIPE_INGREDIENT);
        db.execSQL(SQL_CREATE_RECIPE_STEP);
//        db.execSQL(SQL_CREATE_RECIPE_PICTURE);
//        db.execSQL(SQL_CREATE_LIST);
//        db.execSQL(SQL_CREATE_RECIPE_LIST_MEMBER);
    }

    public boolean isReady(){
        boolean ready = true;
        try {
            Cursor cur = db.rawQuery("SELECT count(*) FROM " + DbContract.Recipe.TABLE_NAME, null);
            cur.moveToNext();
            int rowCount = cur.getInt(0);
            Log.w("RecipeRepo: DbHelper", "Database ready");
        } catch(RuntimeException e){
            ready = false;
            Log.w("RecipeRepo: DbHelper", "Database not ready");
        }
        if(!ready)
            this.populateDb();
        return ready;
    }

    public void displayDatabaseLoadingDialog(Context context, DialogInterface.OnClickListener listener){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("The database is still being loaded. Please try again soon.")
                .setTitle("Database Not Ready")
                .setNeutralButton("Ok", listener);
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void populateDb(){
        if(!this.populateRunning){
            this.populateRunning = true;
            this.dropAllTables();
            this.createAllTables();


            ContentValues values;
            long recipeId;
            long dishRowId;
            long mealRowId;

            values = new ContentValues(1);
            values.put(DishType.COLUMN_NAME, "Entree");
            dishRowId = this.db.insert(DbContract.DishType.TABLE_NAME, null, values);

            values = new ContentValues(1);
            values.put(MealType.COLUMN_NAME, "Lunch");
            mealRowId = this.db.insert(DbContract.MealType.TABLE_NAME, null, values);

            values = new ContentValues(7);
            values.put(Recipe.COLUMN_NAME, "Deviled Eggs");
            values.put(Recipe.COLUMN_PREP_TIME, 30);
            values.put(Recipe.COLUMN_COOK_TIME, 10);
            values.put(Recipe.COLUMN_DESCRIPTION, "Eggs, Mayo, Salt");
            values.put(Recipe.COLUMN_DISH_TYPE_ID, dishRowId);
            values.put(Recipe.COLUMN_MEAL_TYPE_ID, mealRowId);
            values.put(Recipe.COLUMN_OWNER_NAME, "Christopher");
            recipeId = this.db.insert(DbContract.Recipe.TABLE_NAME, null, values);

            values = new ContentValues(3);
            values.put(RecipeIngredient.COLUMN_INGREDIENT, "Eggs");
            values.put(RecipeIngredient.COLUMN_RECIPE_ID, recipeId);
            values.put(RecipeIngredient.COLUMN_SORT, 10);
            this.db.insert(DbContract.RecipeIngredient.TABLE_NAME, null, values);

            values = new ContentValues(3);
            values.put(RecipeIngredient.COLUMN_INGREDIENT, "Mayo");
            values.put(RecipeIngredient.COLUMN_RECIPE_ID, recipeId);
            values.put(RecipeIngredient.COLUMN_SORT, 20);
            this.db.insert(DbContract.RecipeIngredient.TABLE_NAME, null, values);

            values = new ContentValues(3);
            values.put(RecipeIngredient.COLUMN_INGREDIENT, "Salt");
            values.put(RecipeIngredient.COLUMN_RECIPE_ID, recipeId);
            values.put(RecipeIngredient.COLUMN_SORT, 30);
            this.db.insert(DbContract.RecipeIngredient.TABLE_NAME, null, values);

            values = new ContentValues(3);
            values.put(RecipeStep.COLUMN_INSTRUCTION, "Boil Eggs");
            values.put(RecipeStep.COLUMN_RECIPE_ID, recipeId);
            values.put(RecipeStep.COLUMN_SORT, 10);
            this.db.insert(DbContract.RecipeStep.TABLE_NAME, null, values);

            values = new ContentValues(3);
            values.put(RecipeStep.COLUMN_INSTRUCTION, "Mash Eggs");
            values.put(RecipeStep.COLUMN_RECIPE_ID, recipeId);
            values.put(RecipeStep.COLUMN_SORT, 20);
            this.db.insert(DbContract.RecipeStep.TABLE_NAME, null, values);

            values = new ContentValues(3);
            values.put(RecipeStep.COLUMN_INSTRUCTION, "Mix in Mayo and Salt");
            values.put(RecipeStep.COLUMN_RECIPE_ID, recipeId);
            values.put(RecipeStep.COLUMN_SORT, 30);
            this.db.insert(DbContract.RecipeStep.TABLE_NAME, null, values);

            values = new ContentValues(7);
            values.put(Recipe.COLUMN_NAME, "French Fries");
            values.put(Recipe.COLUMN_PREP_TIME, 10);
            values.put(Recipe.COLUMN_COOK_TIME, 30);
            values.put(Recipe.COLUMN_DESCRIPTION, "Potatoes sliced and fried");
            values.put(Recipe.COLUMN_DISH_TYPE_ID, dishRowId);
            values.put(Recipe.COLUMN_MEAL_TYPE_ID, mealRowId);
            values.put(Recipe.COLUMN_OWNER_NAME, "Christopher");
            recipeId = this.db.insert(DbContract.Recipe.TABLE_NAME, null, values);
        }
    }

    public int insertItem(String tableName, HashMap<String, String> data){
        ContentValues values = new ContentValues(data.size());
        for(String k : data.keySet()){
            if(k.equals("_id")) continue;
            values.put(k, data.get(k));
        }
        long rowId = db.insert(tableName, null, values);
        return (int) rowId;
    }

    public int updateItem(String tableName, HashMap<String, String> data){
        ContentValues values = new ContentValues(data.size());
        StringBuilder where = new StringBuilder();
        for(String k : data.keySet()){
            if(k.equals("_id")){
                where.append(k);
                where.append(" = ");
                where.append(data.get(k));
            }
            values.put(k, data.get(k));
        }
        long rowId = db.update(tableName, values, where.toString(), null);
        return (int) rowId;
    }

    public Cursor selectItem(String tableName, List<String> columns, int id){
        HashMap<String, String> wheres = new HashMap<>();
        wheres.put("_id", Integer.valueOf(id).toString());
        return this.selectItem(tableName, columns, wheres);
    }

    public Cursor selectItem(String tableName, List<String> columns, HashMap<String, String> wheres){
        List<String> whereCols = new ArrayList<>(wheres.size());
        List<String> whereArgs = new ArrayList<>(wheres.size());
        for (String key : wheres.keySet()) {
            whereCols.add(key + " = ?");
            whereArgs.add(wheres.get(key));
        }
        String where = TextUtils.join(", ", whereCols);

        String[] columnsArr = new String[columns.size()];
        for(int i = 0; i < columns.size(); i++){
            columnsArr[i] = columns.get(i);
        }

        String[] whereArgsArr = new String[whereArgs.size()];
        for(int i = 0; i < whereArgs.size(); i++){
            whereArgsArr[i] = whereArgs.get(i);
        }
        return this.db.query(tableName, columnsArr, where, whereArgsArr, null, null, null);
    }

    public Cursor getCursorByQuery(String qry, String[] args){
        return db.rawQuery(qry, args);
    }
    public Cursor getCursorByQuery(String qry){
        String[] args = {};
        return this.getCursorByQuery(qry, args);
    }
}