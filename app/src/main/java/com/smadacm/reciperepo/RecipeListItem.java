package com.smadacm.reciperepo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class RecipeListItem extends AppCompatActivity {
    protected int itemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list_item);
    }

    public void setItemId(int id){
        this.itemId = id;
    }
    public int getItemId(){
        return this.itemId;
    }
}
