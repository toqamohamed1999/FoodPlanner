package eg.gov.iti.jets.foodplanner;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import eg.gov.iti.jets.foodplanner.model.Ingredient;
import eg.gov.iti.jets.foodplanner.model.Meal;

public class Meal_Details_Activity extends YouTubeBaseActivity {
    private Meal meal;
    private static final String TAG = "Meal_Details_Activity";
    ImageView meal_details_imageView, mealDetails_card_fav_imageview;
    ImageButton resultSearch_back_imageBtn;
    TextView mealDetails_mealName_txtView, mealDetails_mealCateVal_txtView, mealDetails_mealAreaVal_txtView, mealDetails_stepsVal_txtView;
    RecyclerView mealDetails_ingredientsList_recycleView;
    YouTubePlayerView youTubePlayerView;
    List<Ingredient> ingredientList = new ArrayList<>();
    IngredientsAdapter ingredientsAdapter;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_details);

        iniUI();
        getMeal();
        updateUI();
    }

    private void iniUI() {
        meal_details_imageView = findViewById(R.id.meal_details_imageView);
        mealDetails_card_fav_imageview = findViewById(R.id.mealDetails_card_fav_imageview);
        resultSearch_back_imageBtn = findViewById(R.id.resultSearch_back_imageBtn);
        mealDetails_mealName_txtView = findViewById(R.id.mealDetails_mealName_txtView);
        mealDetails_ingredientsList_recycleView = findViewById(R.id.mealDetails_ingredientsList_recycleView);
        mealDetails_mealCateVal_txtView = findViewById(R.id.mealDetails_mealCateVal_txtView);
        mealDetails_mealAreaVal_txtView = findViewById(R.id.mealDetails_mealAreaVal_txtView);
        mealDetails_stepsVal_txtView = findViewById(R.id.mealDetails_stepsVal_txtView);
        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.videoView);
        progressBar = findViewById(R.id.meal_details_progressbar);
    }

    public void updateUI() {
//        Picasso.get().load(meal.getStrMealThumb())
//                .placeholder(R.mipmap.ic_launcher)
//                .into(meal_details_imageView);

        Picasso.with(getApplicationContext()).load(meal.getStrMealThumb())
                .into(meal_details_imageView, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        if (progressBar != null) {
                            progressBar.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });
        setupRecyclerView();
        mealDetails_mealName_txtView.setText(meal.getStrMeal());
        mealDetails_mealCateVal_txtView.setText(meal.getStrCategory());
        mealDetails_mealAreaVal_txtView.setText(meal.getStrArea());
        mealDetails_stepsVal_txtView.setText(meal.getStrInstructions());
        youTubePlayerView.initialize(YouTupeConfig.API_KEY, new YouTubePlayer.OnInitializedListener() {

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                String[] url = meal.getStrYoutube().split("=");
                youTubePlayer.loadVideo(url[1]);
                youTubePlayer.play();
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(getApplicationContext(), "Video player Failed", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onInitializationFailure: "+youTubeInitializationResult.name());

            }
        });


    }

    public List<Ingredient> getIngredientList() {
        ingredientList.add(new Ingredient(meal.getStrIngredient1(), "https://www.themealdb.com/images/ingredients/Lime.png"));
        ingredientList.add(new Ingredient(meal.getStrIngredient2(), "https://www.themealdb.com/images/ingredients/Lime.png"));
        ingredientList.add(new Ingredient(meal.getStrIngredient3(), "https://www.themealdb.com/images/ingredients/Lime.png"));
        ingredientList.add(new Ingredient(meal.getStrIngredient4(), "https://www.themealdb.com/images/ingredients/Lime.png"));
        ingredientList.add(new Ingredient(meal.getStrIngredient5(), "https://www.themealdb.com/images/ingredients/Lime.png"));
        ingredientList.add(new Ingredient(meal.getStrIngredient6(), "https://www.themealdb.com/images/ingredients/Lime.png"));
        ingredientList.add(new Ingredient(meal.getStrIngredient7(), "https://www.themealdb.com/images/ingredients/Lime.png"));
        ingredientList.add(new Ingredient(meal.getStrIngredient8(), "https://www.themealdb.com/images/ingredients/Lime.png"));
        ingredientList.add(new Ingredient(meal.getStrIngredient9(), "https://www.themealdb.com/images/ingredients/Lime.png"));
        ingredientList.add(new Ingredient(meal.getStrIngredient10(), "https://www.themealdb.com/images/ingredients/Lime.png"));
        ingredientList.add(new Ingredient(meal.getStrIngredient11(), "https://www.themealdb.com/images/ingredients/Lime.png"));
        ingredientList.add(new Ingredient(meal.getStrIngredient12(), "https://www.themealdb.com/images/ingredients/Lime.png"));
        ingredientList.add(new Ingredient(meal.getStrIngredient13(), "https://www.themealdb.com/images/ingredients/Lime.png"));
        ingredientList.add(new Ingredient(meal.getStrIngredient14(), "https://www.themealdb.com/images/ingredients/Lime.png"));
        ingredientList.add(new Ingredient(meal.getStrIngredient15(), "https://www.themealdb.com/images/ingredients/Lime.png"));
        ingredientList.add(new Ingredient(meal.getStrIngredient16(), "https://www.themealdb.com/images/ingredients/Lime.png"));
        ingredientList.add(new Ingredient(meal.getStrIngredient17(), "https://www.themealdb.com/images/ingredients/Lime.png"));
        ingredientList.add(new Ingredient(meal.getStrIngredient18(), "https://www.themealdb.com/images/ingredients/Lime.png"));
        ingredientList.add(new Ingredient(meal.getStrIngredient19(), "https://www.themealdb.com/images/ingredients/Lime.png"));
        ingredientList.add(new Ingredient(meal.getStrIngredient20(), "https://www.themealdb.com/images/ingredients/Lime.png"));
        Log.i(TAG, "getIngredientList: "+ingredientList.size());
        
        ingredientList=ingredientList.stream().filter(e->!(e.getStrIngredient().equals(""))).collect(Collectors.toList());
        return ingredientList;
    }

    private void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Meal_Details_Activity.this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        mealDetails_ingredientsList_recycleView.setLayoutManager(linearLayoutManager);
        ingredientsAdapter = new IngredientsAdapter(Meal_Details_Activity.this, ingredientList);
        mealDetails_ingredientsList_recycleView.setAdapter(ingredientsAdapter);
        ingredientList = getIngredientList();
        ingredientsAdapter.setData(ingredientList);
    }

    private Meal getMeal() {
        Intent i = getIntent();
        meal = (Meal) i.getSerializableExtra(MealAdapter.MEAL_KEY);
        return meal;
    }
}