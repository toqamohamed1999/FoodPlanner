package eg.gov.iti.jets.foodplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.squareup.picasso.Picasso;

import eg.gov.iti.jets.foodplanner.model.Meal;

public class Meal_Details_Activity extends AppCompatActivity {
    private Meal meal;
    private static final String TAG = "Meal_Details_Activity";
    ImageView meal_details_imageView,mealDetails_card_fav_imageview;
    ImageButton resultSearch_back_imageBtn;
    TextView mealDetails_mealName_txtView,mealDetails_mealCateVal_txtView,mealDetails_mealAreaVal_txtView,mealDetails_stepsVal_txtView;
    RecyclerView mealDetails_ingredientsList_recycleView;
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_details);


        getSupportActionBar().hide();
        getMeal();
        iniUI();
        updateUI();
    }
    private void iniUI(){
        meal_details_imageView=findViewById(R.id.meal_details_imageView);
        mealDetails_card_fav_imageview=findViewById(R.id.mealDetails_card_fav_imageview);
        resultSearch_back_imageBtn=findViewById(R.id.resultSearch_back_imageBtn);
        mealDetails_mealName_txtView=findViewById(R.id.mealDetails_mealName_txtView);
        mealDetails_ingredientsList_recycleView=findViewById(R.id.mealDetails_ingredientsList_recycleView);
        mealDetails_mealCateVal_txtView=findViewById(R.id.mealDetails_mealCateVal_txtView);
        mealDetails_mealAreaVal_txtView=findViewById(R.id.mealDetails_mealAreaVal_txtView);
        mealDetails_stepsVal_txtView=findViewById(R.id.mealDetails_stepsVal_txtView);
        videoView=findViewById(R.id.videoView);
    }

    public void updateUI() {
        Picasso.get().load(meal.getStrMealThumb())
                .placeholder(R.mipmap.ic_launcher)
                .into(meal_details_imageView);
        //videoView
        //mealDetails_ingredientsList_recycleView
        mealDetails_mealName_txtView.setText(meal.getStrMeal());
        mealDetails_mealCateVal_txtView.setText(meal.getStrCategory());
        mealDetails_mealAreaVal_txtView.setText(meal.getStrArea());
        mealDetails_stepsVal_txtView.setText(meal.getStrInstructions());

    }

    private Meal getMeal() {
        Intent i = getIntent();
        meal = (Meal) i.getSerializableExtra(MealAdapter.MEAL_KEY);
        return meal;
    }
}