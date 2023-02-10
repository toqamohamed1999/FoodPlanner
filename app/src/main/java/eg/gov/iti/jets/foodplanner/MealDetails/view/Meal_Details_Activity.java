package eg.gov.iti.jets.foodplanner.MealDetails.view;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
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
import eg.gov.iti.jets.foodplanner.MyDialog;
import eg.gov.iti.jets.foodplanner.MySharedPref;
import eg.gov.iti.jets.foodplanner.R;
import eg.gov.iti.jets.foodplanner.YouTubeConfig;
import eg.gov.iti.jets.foodplanner.authentication.view.LoginActivity;
import eg.gov.iti.jets.foodplanner.database.LocalSource;
import eg.gov.iti.jets.foodplanner.model.Ingredient;
import eg.gov.iti.jets.foodplanner.model.Meal;
import eg.gov.iti.jets.foodplanner.model.PlanMeal;
import eg.gov.iti.jets.foodplanner.model.Repo;
import eg.gov.iti.jets.foodplanner.network.RemoteSource;
import eg.gov.iti.jets.foodplanner.plan.view.PlanMealsAdapter;

public class Meal_Details_Activity extends YouTubeBaseActivity implements MealDetailsViewInterface {
    private Meal meal;
    private PlanMeal planMeal = new PlanMeal();
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
    MySharedPref mySharedPref;

    boolean isFavMeal = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_details);

        iniUI();
        init();
        setupRecyclerView();
        getMeal();
        checkFav();
        setUpAutoCompleteTv();
        handelBackButton();
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
        mySharedPref = new MySharedPref(getApplicationContext());

        handleMealAddToFav();
    }

    public void init() {
        mealDetailsPresenter = new MealDetailsPresenter(this, Repo.getInstance(getApplicationContext(), LocalSource.getLocalSource(getApplicationContext()), RemoteSource.getRemoteSource()));
    }

    private void checkFav() {
        if (meal != null) mealDetailsPresenter.MealIsExistInFav(meal.getIdMeal());
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
        selectedDay = searchByList.get(0);

        setOnSelectFilterEvent();
    }

    private void setOnSelectFilterEvent() {
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mySharedPref.sharedPrefRead().getEmail().equals("not found")) {
                    AlertDialog.Builder builder = MyDialog.myDialog(Meal_Details_Activity.this);
                    builder.setMessage("Do yo want to login?");
                    builder.setTitle("You are not logged in!");
                    builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                        // finish();
                        Intent i = new Intent(Meal_Details_Activity.this, LoginActivity.class);
                        startActivity(i);
                    });

                    builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                        dialog.cancel();
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                } else {
                    selectedDay = parent.getItemAtPosition(position).toString();
                    planMeal = planMeal.getPlanMealFromMeal(meal, selectedDay);
                    mealDetailsPresenter.insertPlanMeal(planMeal);
                    Toast.makeText(Meal_Details_Activity.this, "Meal Added to " + selectedDay, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void handelBackButton() {
        resultSearch_back_imageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void handleMealAddToFav() {
        mealDetails_card_fav_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mySharedPref.sharedPrefRead().getEmail().equals("not found")) {
                    AlertDialog.Builder builder = MyDialog.myDialog(Meal_Details_Activity.this);
                    builder.setMessage("Do yo want to login?");
                    builder.setTitle("You are not logged in!");
                    builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                        // finish();
                        Intent i = new Intent(Meal_Details_Activity.this, LoginActivity.class);
                        startActivity(i);
                    });

                    builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                        dialog.cancel();
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                } else {
                    if (isFavMeal) {
                        mealDetailsPresenter.deleteFavMeal(meal);
                        mealDetails_card_fav_imageview.setImageResource(R.drawable.baseline_favorite_24);
                        isFavMeal = false;
                        Toast.makeText(Meal_Details_Activity.this, "Removed From Favourite ", Toast.LENGTH_SHORT).show();
                    } else {
                        mealDetailsPresenter.insertFavMeal(meal);
                        mealDetails_card_fav_imageview.setImageResource(R.drawable.ic_baseline_favorite_red);
                        isFavMeal = true;
                        Toast.makeText(Meal_Details_Activity.this, "Added To Favourite.. ", Toast.LENGTH_SHORT).show();
                    }
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

        mealDetails_mealName_txtView.setText(meal.getStrMeal());
        mealDetails_mealCateVal_txtView.setText(meal.getStrCategory());
        mealDetails_mealAreaVal_txtView.setText(meal.getStrArea());
        mealDetails_stepsVal_txtView.setText(meal.getStrInstructions());
        ingredientList = getIngredientList();
        ingredientsAdapter.setData(ingredientList);

        handleYoutubeEvent();
    }

    private void handleYoutubeEvent() {
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
    }


    @Override
    public void checkMealIsFav(boolean isFav) {
        isFavMeal = isFav;
        if (isFav) {
            mealDetails_card_fav_imageview.setImageResource(R.drawable.ic_baseline_favorite_red);
        }
    }


    private void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Meal_Details_Activity.this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        mealDetails_ingredientsList_recycleView.setLayoutManager(linearLayoutManager);
        ingredientsAdapter = new IngredientsAdapter(Meal_Details_Activity.this, ingredientList);
        mealDetails_ingredientsList_recycleView.setAdapter(ingredientsAdapter);
        ingredientsAdapter.setData(ingredientList);
    }

    private void getMeal() {
        Intent i = getIntent();
        if (i.getStringExtra("adapterType").equals(MealAdapter.MEAL_ADAPTER_TYPE)) {
            meal = (Meal) i.getSerializableExtra(MealAdapter.MEAL_KEY);
        } else {
            planMeal = (PlanMeal) i.getSerializableExtra(PlanMealsAdapter.PlAN_MEAL_KEY);
            meal = planMeal.getMealFromMealPlanMeal(planMeal);
        }
        if (meal.getStrCategory() == null) {
            mealDetailsPresenter.getMealDetailsById(meal.getIdMeal());
        } else {
            updateUI();

        }
    }

    @Override
    public void getMealDetailsById(Meal myMeal) {
        meal = myMeal;
        if (meal.getStrCategory() != null) {
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