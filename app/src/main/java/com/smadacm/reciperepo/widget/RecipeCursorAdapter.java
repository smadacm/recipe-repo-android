package com.smadacm.reciperepo.widget;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.smadacm.reciperepo.R;

public class RecipeCursorAdapter extends CursorAdapter {
    protected LayoutInflater cursorInflator;

    public RecipeCursorAdapter(Context context, Cursor cursor){
        super(context, cursor, 0);
        this.cursorInflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent){
        return this.cursorInflator.inflate(R.layout.activity_recipe_list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor){
        // Find fields to populate in inflated template
        TextView tvTitle = (TextView) view.findViewById(R.id.recipeListItemTitle);
        TextView tvSubtitle = (TextView) view.findViewById(R.id.recipeListItemSubtitle);

        // Extract properties from cursor
        String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
        String description = cursor.getString(cursor.getColumnIndexOrThrow("description"));

        // Populate fields with extracted properties
        tvTitle.setText(name);
        tvSubtitle.setText(description);
    }
}
