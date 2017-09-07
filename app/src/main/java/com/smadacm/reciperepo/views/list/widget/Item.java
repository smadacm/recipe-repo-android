package com.smadacm.reciperepo.views.list.widget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.smadacm.reciperepo.R;

public class Item extends AppCompatActivity {
    protected int itemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_list_item);
    }

    public void setItemId(int id){
        this.itemId = id;
    }
    public int getItemId(){
        return this.itemId;
    }
}
