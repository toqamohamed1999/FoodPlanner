package eg.gov.iti.jets.foodplanner.MealDetails.view;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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

import eg.gov.iti.jets.foodplanner.MealAdapter;
import eg.gov.iti.jets.foodplanner.MealDetails.presenter.MealDetailsPresenter;
import eg.gov.iti.jets.foodplanner.R;
import eg.gov.iti.jets.foodplanner.YouTubeConfig;
import eg.gov.iti.jets.foodplanner.database.LocalSource;
import eg.gov.iti.jets.foodplanner.model.Ingredient;
import eg.gov.iti.jets.foodplanner.model.Meal;
import eg.gov.iti.jets.foodplanner.model.PlanMeal;
import eg.gov.iti.jets.foodplanner.model.Repo;
import eg.gov.iti.jets.foodplanner.network.RemoteSource;

public class Meal_Details_Activity extends YouTubeBaseActivity implements MealDetailsViewInterface {
    private Meal meal;
    private PlanMeal planMeal;
    private static final String TAG = "Meal_Details_Activity";
    ImageView meal_details_imageView, mealDetails_card_fav_imageview;
    ImageButton resultSearch_back_imageBtn;
    TextView mealDetails_mealName_txtView, mealDetails_mealCateVal_txtView, mealDetails_mealAreaVal_txtView, mealDetails_stepsVal_txtView;
    RecyclerView mealDetails_ingredientsList_recycleView;
    YouTubePlayerView youTubePlayerView;
    List<Ingredient> ingredientList = new ArrayList<>();
    IngredientsAdapter ingredientsAdapter;
    MealDetailsPresenter mealDetailsPresenter;
    AutoCompleteTextView autoCompleteTextView;

    private ArrayAdapter<String> arrayAdapter;

    private ProgressBar progressBar;
    String selectedDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_details);

        iniUI();
        init();
        getMeal();
        setUpAutoCompleteTv();
    }

    private void setUpAutoCompleteTv() {
        ArrayList<String> searchByList = new ArrayList<>();
        searchByList.add("Saturday");
        searchByList.add("Sunday");
        searchByList.add("Monday");
        searchByList.add("Tuesday");
        searchByList.add("Wednesday");
        searchByList.add("Thursday");
        searchByList.add("Friday");

        arrayAdapter = new ArrayAdapter<>(Meal_Details_Activity.this, android.R.layout.simple_list_item_1, searchByList);
        autoCompleteTextView.setAdapter(arrayAdapter);
       // autoCompleteTextView.setText(arrayAdapter.getItem(0), false);
        selectedDay = searchByList.get(0);

        setOnSelectFilterEvent();
    }
    private void setOnSelectFilterEvent() {
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedDay = parent.getItemAtPosition(position).toString();
                planMeal = planMeal.getPlanMealFromMeal(meal, selectedDay);
                mealDetailsPresenter.insertPlanMeal(planMeal);
                Toast.makeText(Meal_Details_Activity.this, "Meal Added to "+selectedDay, Toast.LENGTH_SHORT).show();
            }
        });
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

        autoCompleteTextView = findViewById(R.id.mealDetails_autoCompleteTextView);
        mealDetails_card_fav_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MealIsExistInFav(meal.getIdMeal())) {
                    mealDetailsPresenter.deleteFavMeal(meal);
                    mealDetails_card_fav_imageview.setImageResource(R.drawable.baseline_favorite_24);
                    Toast.makeText(Meal_Details_Activity.this, "Removed From Favourite ", Toast.LENGTH_SHORT).show();
                } else {
                    mealDetailsPresenter.insertFavMeal(meal);
                    mealDetails_card_fav_imageview.setImageResource(R.drawable.ic_baseline_favorite_red);
                    Toast.makeText(Meal_Details_Activity.this, "Added To Favourite.. ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void updateUI() {
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
        youTubePlayerView.initialize(YouTubeConfig.API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                String[] url = meal.getStrYoutube().split("=");
                youTubePlayer.loadVideo(url[1]);
                youTubePlayer.play();
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(getApplicationContext(), "Video player Failed", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onInitializationFailure: " + youTubeInitializationResult.name());

            }
        });
        if (MealIsExistInFav(meal.getIdMeal())) {
            mealDetails_card_fav_imageview.setImageResource(R.drawable.ic_baseline_favorite_red);
        }
    }

    public void init() {
        mealDetailsPresenter = new MealDetailsPresenter(this, Repo.getInstance(getApplicationContext(), LocalSource.getLocalSource(getApplicationContext()), RemoteSource.getRemoteSource()));
        planMeal = new PlanMeal();
    }

    boolean MealIsExistInFav(String idMeal) {
        Log.i(TAG, "MealIsExistInFav: meal details activity " + mealDetailsPresenter.MealIsExistInFav(idMeal));
        return mealDetailsPresenter.MealIsExistInFav(idMeal);
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

    private void getMeal() {
        Intent i = getIntent();
        meal = (Meal) i.getSerializableExtra(MealAdapter.MEAL_KEY);
        if(meal.getStrCategory() == null){
            mealDetailsPresenter.getMealDetailsById(meal.getIdMeal());
        }else{
            updateUI();
        }
    }

    @Override
    public void getMealDetailsById(Meal myMeal) {
        meal = myMeal;
        if(meal.getStrCategory() != null){
            updateUI();
        }
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
        Log.i(TAG, "getIngredientList: " + ingredientList.size());

        ingredientList = ingredientList.stream().filter(e -> !(e.getStrIngredient().equals(""))).collect(Collectors.toList());
        return ingredientList;
    }
}