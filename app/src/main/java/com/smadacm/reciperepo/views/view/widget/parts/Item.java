package com.smadacm.reciperepo.views.view.widget.parts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.smadacm.reciperepo.R;

public class Item extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_recipe_parts_item);
    }
}
