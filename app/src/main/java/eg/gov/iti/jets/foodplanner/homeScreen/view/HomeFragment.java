package eg.gov.iti.jets.foodplanner.homeScreen.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import eg.gov.iti.jets.foodplanner.Meal_Details_Activity;
import eg.gov.iti.jets.foodplanner.database.LocalSource;
import eg.gov.iti.jets.foodplanner.database.LocalSourceInterface;
import eg.gov.iti.jets.foodplanner.model.Meal;
import eg.gov.iti.jets.foodplanner.MealAdapter;
import eg.gov.iti.jets.foodplanner.ProfileActivity;
import eg.gov.iti.jets.foodplanner.R;
import eg.gov.iti.jets.foodplanner.homeScreen.presenter.HomePresenter;
import eg.gov.iti.jets.foodplanner.model.Repo;
import eg.gov.iti.jets.foodplanner.network.RemoteSource;

public class HomeFragment extends Fragment implements HomeViewInterface{

    private static final String TAG = "HomeFragment";

    private RecyclerView recyclerView;

    private List<Meal> mealsList = new ArrayList<>();

    private MealAdapter mealAdapter ;

    private ImageView profileImageView;

    private  Meal meal;

    private HomePresenter homePresenter;

    private ImageView inspirationImageView,inspirationFavImageView;

    private TextView inspirationNameTv,inspirationCategoryTv;
    private CardView inspiration_meal_cardView;


    public HomeFragment() {
        // Required empty public constructor
    }


    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUI(view);
        setupRecyclerView();
        onClickProfileImage();
        setUpPresenter();
    }

    private void initUI(View view){
        recyclerView = view.findViewById(R.id.home_category_recyclerView);
        profileImageView = view.findViewById(R.id.home_profile_imageView);
        inspirationImageView = view.findViewById(R.id.home_card_imageview);
        inspirationFavImageView = view.findViewById(R.id.home_card_fav_imageview);
        inspirationNameTv = view.findViewById(R.id.home_card_title_textview);
        inspirationCategoryTv = view.findViewById(R.id.home_card_category_textView);
        inspiration_meal_cardView=view.findViewById(R.id.inspiration_meal_cardView);
        inspiration_meal_cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(requireContext(), Meal_Details_Activity.class);
                i.putExtra(MealAdapter.MEAL_KEY,meal);
                startActivity(i);
            }
        });
    }

    private void onClickProfileImage(){
        profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireContext(), ProfileActivity.class));
            }
        });
    }

   private void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        mealAdapter = new MealAdapter(requireContext(), mealsList);
        recyclerView.setAdapter(mealAdapter);
    }


    void setUpPresenter(){
        homePresenter = new HomePresenter(this,Repo.getInstance(requireContext(), LocalSource.getLocalSource(requireContext()), RemoteSource.getRemoteSource()));
        homePresenter.getRandomMeal();
        homePresenter.getEgyptianMeals();
        homePresenter.getMealById();
    }
    @Override
    public void getRandomMeal(Meal meal) {
        this.meal = meal;
        setInspirationMealData();
        Log.i(TAG, "getRandomMeal: "+meal);
    }



    private void setInspirationMealData(){
        if(meal != null) {
            Picasso.get().load(meal.getStrMealThumb())
                    .placeholder(R.mipmap.ic_launcher)
                    .into(inspirationImageView);
            inspirationNameTv.setText(meal.getStrMeal());
            inspirationCategoryTv.setText(meal.getStrCategory());
        }
    }

    @Override
    public void getEgyptianMeals(List<Meal> mealsList) {
        Log.i(TAG, "getEgyptianMeals: "+ mealsList.toString());
        this.mealsList = mealsList;
        mealAdapter.setData(mealsList);
    }

    @Override
    public void getMealById(Meal meal) {
        Meal meal1 = meal;
        Log.i(TAG, "getMealById: "+meal1);

    }
}