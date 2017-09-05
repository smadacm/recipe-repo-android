package com.smadacm.reciperepo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;

import com.smadacm.reciperepo.db.DbHelper;
import com.smadacm.reciperepo.widget.RecipeCursorAdapter;

import java.util.List;

public class RecipeList extends AppCompatActivity {

    protected DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("RecipeRepo", "Starting Up");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        try {
            this.dbHelper = new DbHelper(this);

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
                    int ii = 0; // A line on which I can set a breakpoint
                }
            });
        } catch (Exception e){
            Log.e("RecipeRepo: " + e.getClass().getName(), e.getMessage(), e);
            throw e;
        }
    }
}
