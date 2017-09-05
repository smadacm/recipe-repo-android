package com.smadacm.reciperepo.network;

import android.content.Context;

import com.smadacm.reciperepo.db.DbHelper;
import com.smadacm.reciperepo.model.db.DishType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RecipeLoader extends AbstractDbDependentNetworkCall {
    protected String url = "http://recipe.smadacm.com/recipe/api/dump/";

    public RecipeLoader(Context ctx){
        super(ctx);
    }

    public void afterAsync(String result){
        JSONObject dump;
        JSONArray dishTypes;
        String msg;

        try {
            dump = new JSONObject(result);
            dishTypes = dump.getJSONArray("dish_types");

            for(int i=0; i < dishTypes.length(); i++){
                JSONObject dishTypeJson = dishTypes.getJSONObject(i);
                String name = dishTypeJson.getString("name");
                int remoteId = dishTypeJson.getInt("remote_id");
//                DishType dishType = new DishType(this.context, name, remoteId);

            }

            JSONArray mealTypes = dump.getJSONArray("meal_types");
        } catch(JSONException e){
            // log unable to parse dish types
            return;
        }
    }
}
