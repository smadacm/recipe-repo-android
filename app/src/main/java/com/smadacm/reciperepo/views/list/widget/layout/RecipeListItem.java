package com.smadacm.reciperepo.views.list.widget.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class RecipeListItem extends LinearLayout {
    protected int recipeId;

    public RecipeListItem(Context context) {
        super(context, (AttributeSet)null, 0, 0);
    }

    public RecipeListItem(Context context, AttributeSet attrs) {
        super(context, attrs, 0, 0);
    }

    public RecipeListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr, 0);
    }

    public RecipeListItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setRecipeId(int id){
        this.recipeId = id;
    }
    public int getRecipeId(){
        return this.recipeId;
    }
}
