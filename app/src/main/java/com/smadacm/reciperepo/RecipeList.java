package com.smadacm.reciperepo;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.smadacm.reciperepo.db.DbHelper;
import com.smadacm.reciperepo.widget.RecipeListItem;
import com.smadacm.reciperepo.widget.RecipeCursorAdapter;

public class RecipeList extends AppCompatActivity {

    public static int REQUEST_CODE_VIEW_RECIPE = 1;

    protected DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("RecipeRepo", "Starting Up");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        try {
            this.dbHelper = new DbHelper(this);
            this.dbHelper.populateDb();

            StringBuilder msg = new StringBuilder();
            Cursor cur = dbHelper.getCursorByQuery("SELECT _id, name, description FROM recipe ORDER BY _id DESC");
            msg.append("Number of records: ");
            msg.append(cur.getCount());
            Log.d("RecipeRepo", msg.toString());
            for(int i=0; i<cur.getCount(); i++){
                msg = new StringBuilder();
                cur.moveToPosition(i);
                msg.append("    Record: ");
                msg.append(cur.getString(cur.getColumnIndexOrThrow("_id")));
                msg.append(" -- ");
                msg.append(cur.getString(cur.getColumnIndexOrThrow("name")));
                msg.append(" -- ");
                msg.append(cur.getString(cur.getColumnIndexOrThrow("description")));
                Log.d("RecipeRepo", msg.toString());
            }
            cur.moveToFirst();
            RecipeCursorAdapter adapter = new RecipeCursorAdapter(this, cur);

            ListView listView = (ListView) findViewById(R.id.recipe_list_list);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    RecipeListItem rli = (RecipeListItem) view;
                    int ii = 0; // A line on which I can set a breakpoint
                    Intent intent = new Intent(RecipeList.this, ViewRecipe.class);
                    intent.putExtra("recipe_id", rli.getRecipeId());
                    startActivityForResult(intent, RecipeList.REQUEST_CODE_VIEW_RECIPE);
                }
            });
        } catch (Exception e){
            Log.e("RecipeRepo: " + e.getClass().getName(), e.getMessage(), e);
            throw e;
        }
    }
}
