package com.smadacm.reciperepo.views.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import com.smadacm.reciperepo.R;
import com.smadacm.reciperepo.model.db.Recipe;
import com.smadacm.reciperepo.model.db.RecipeIngredient;
import com.smadacm.reciperepo.model.db.RecipeStep;
import com.smadacm.reciperepo.views.view.widget.parts.ExpandableListAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class ViewRecipe extends AppCompatActivity {

    public static int RESULT_OK = 1;
    public static int RESULT_RECIPE_NOT_FOUND = 2;
    public static int RESULT_UNKNOWN_ERROR = 16;

    protected Recipe recipe;
    protected List<RecipeStep> recipeStepList;
    protected List<RecipeIngredient> recipeIngredientList;
    protected TextView descriptionTarget;
//    protected ExpandableListView ingredientsTarget;
//    protected ExpandableListView stepsTarget;
    protected ExpandableListView partsTarget;

    protected HashMap<String, List<String>> partsAll;
    protected List<String> partsHeader;
    protected ExpandableListAdapter partsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("RecipeRepo", "Creating ViewRecipe");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe);

        int recipeId = this.getIntent().getIntExtra("recipe_id", -1);

        if(recipeId == -1){
            this.setResult(ViewRecipe.RESULT_RECIPE_NOT_FOUND);
            this.finish();
            return;
        }

        this.setResult(ViewRecipe.RESULT_OK);

        this.loadData(recipeId);
        this.loadTargets();
        this.populateTargets();
    }

    protected void loadTargets(){
        this.descriptionTarget = (TextView) findViewById(R.id.recipeDescription);
//        this.ingredientsTarget = (ExpandableListView) findViewById(R.id.recipeIngredients);
//        this.stepsTarget = (ExpandableListView) findViewById(R.id.recipeSteps);
        this.partsTarget = (ExpandableListView) findViewById(R.id.recipeParts);

    }

    protected void populateTargets(){
        this.descriptionTarget.setText(this.recipe.getDescription());

        this.partsAdapter = new ExpandableListAdapter(this, this.partsHeader, this.partsAll);
        this.partsTarget.setAdapter(this.partsAdapter);

        for(int i = 0; i < this.partsHeader.size(); i++){
            this.partsTarget.expandGroup(i);
        }

        setTitle(recipe.getName());
    }

    protected void loadData(int recipeId){
        Recipe recipe = new Recipe(this);
        recipe.load(recipeId);
        this.recipe = recipe;
        this.recipeIngredientList = recipe.getIngredients();
        this.recipeStepList = recipe.getSteps();

        Collections.sort(this.recipeIngredientList, new Comparator<RecipeIngredient>() {
            @Override
            public int compare(RecipeIngredient recipeIngredientOne, RecipeIngredient recipeIngredientTwo) {
                return recipeIngredientOne.getSort() < recipeIngredientTwo.getSort() ? -1 : (recipeIngredientOne.getSort() > recipeIngredientTwo.getSort()) ? 1 : 0;
            }
        });

        Collections.sort(this.recipeStepList, new Comparator<RecipeStep>() {
            @Override
            public int compare(RecipeStep recipeStepOne, RecipeStep recipeStepTwo) {
                return recipeStepOne.getSort() < recipeStepTwo.getSort() ? -1 : (recipeStepOne.getSort() > recipeStepTwo.getSort()) ? 1 : 0;
            }
        });

        this.partsAll = new HashMap<>(2);

        List<String> childTmp = new ArrayList<>(this.recipeIngredientList.size());
        for(int i = 0; i < this.recipeIngredientList.size(); i++){
            childTmp.add(this.recipeIngredientList.get(i).getIngredient());
        }
        this.partsAll.put("Ingredients", childTmp);

        childTmp = new ArrayList<>(this.recipeStepList.size());
        for(int i = 0; i < this.recipeStepList.size(); i++){
            childTmp.add(this.recipeStepList.get(i).getInstruction());
        }
        this.partsAll.put("Steps", childTmp);

        this.partsHeader = new ArrayList<>(this.partsAll.keySet());
    }
}
