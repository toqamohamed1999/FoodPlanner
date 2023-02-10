package eg.gov.iti.jets.foodplanner.homeScreen.view;

import static eg.gov.iti.jets.foodplanner.MealAdapter.MEAL_ADAPTER_TYPE;

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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import eg.gov.iti.jets.foodplanner.MealDetails.view.Meal_Details_Activity;
import eg.gov.iti.jets.foodplanner.NetworkChecker;
import eg.gov.iti.jets.foodplanner.favorites.view.FavInsertListener;
import eg.gov.iti.jets.foodplanner.database.LocalSource;
import eg.gov.iti.jets.foodplanner.model.Meal;
import eg.gov.iti.jets.foodplanner.MealAdapter;
import eg.gov.iti.jets.foodplanner.profile.view.ProfileActivity;
import eg.gov.iti.jets.foodplanner.R;
import eg.gov.iti.jets.foodplanner.homeScreen.presenter.HomePresenter;
import eg.gov.iti.jets.foodplanner.model.Repo;
import eg.gov.iti.jets.foodplanner.network.RemoteSource;
import io.reactivex.rxjava3.core.Observable;

public class HomeFragment extends Fragment implements HomeViewInterface, FavInsertListener {

    private static final String TAG = "HomeFragment";

    private RecyclerView recyclerView;

    private List<Meal> mealsList = new ArrayList<>();

    private MealAdapter mealAdapter;

    private ImageView profileImageView;

    private Meal meal;

    private HomePresenter homePresenter;

    private ImageView inspirationImageView, inspirationFavImageView;

    private TextView inspirationNameTv, inspirationCategoryTv;
    private CardView inspiration_meal_cardView;

    private ProgressBar progressBar;

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

    private void initUI(View view) {
        recyclerView = view.findViewById(R.id.home_category_recyclerView);
        profileImageView = view.findViewById(R.id.home_profile_imageView);
        inspirationImageView = view.findViewById(R.id.home_card_imageview);
        inspirationFavImageView = view.findViewById(R.id.home_card_fav_imageview);
        inspirationNameTv = view.findViewById(R.id.home_card_title_textview);
        inspirationCategoryTv = view.findViewById(R.id.home_card_category_textView);
        inspiration_meal_cardView = view.findViewById(R.id.inspiration_meal_cardView);
        progressBar = view.findViewById(R.id.home_card_progressbar);
        inspiration_meal_cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(requireContext(), Meal_Details_Activity.class);
                i.putExtra(MealAdapter.MEAL_KEY, meal);
                i.putExtra("adapterType", MEAL_ADAPTER_TYPE);
                startActivity(i);
            }
        });
        inspirationFavImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inspirationFavImageView.setImageResource(R.drawable.ic_baseline_favorite_red);
                homePresenter.insertFavMeal(meal);
                Toast.makeText(getActivity(), "Added To Favourite..", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void onClickProfileImage() {
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
        mealAdapter = new MealAdapter(requireContext(), mealsList, this);
        recyclerView.setAdapter(mealAdapter);
    }


    void setUpPresenter() {
        homePresenter = new HomePresenter(this, Repo.getInstance(requireContext(), LocalSource.getLocalSource(requireContext()), RemoteSource.getRemoteSource()));
        if (NetworkChecker.isNetworkAvailable(requireContext())) {
            homePresenter.getRandomMeal();
            homePresenter.getEgyptianMeals();
            homePresenter.getMealById();
        } else {

//            Snackbar snackbar = Snackbar
//                    .make(requireView(), "No Network Connection!", Snackbar.LENGTH_LONG)
//                    .setAction("UNDO", new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            Snackbar snackbar1 = Snackbar.make(requireView(), "No Network Connection!", Snackbar.LENGTH_SHORT);
//                            snackbar1.show();
//                        }
//                    });
//
//            snackbar.show();
            Snackbar.make(getActivity().findViewById(android.R.id.content),
                    "No Network Connection!", Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void getRandomMeal(Meal meal) {
        this.meal = meal;
        setInspirationMealData();
        Log.i(TAG, "getRandomMeal: " + meal);
    }


    private void setInspirationMealData() {
        if (meal != null) {

            Picasso.with(requireContext()).load(meal.getStrMealThumb())
                    .into(inspirationImageView, new com.squareup.picasso.Callback() {
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
            inspirationNameTv.setText(meal.getStrMeal());
            inspirationCategoryTv.setText(meal.getStrCategory());
        }
    }

    @Override
    public void getEgyptianMeals(List<Meal> mealsList) {
        Log.i(TAG, "getEgyptianMeals: " + mealsList.toString());
        this.mealsList = mealsList;
        mealAdapter.setData(mealsList);
    }

    @Override
    public void getMealById(Meal meal) {
        Meal meal1 = meal;
        Log.i(TAG, "getMealById: " + meal1);

    }

    @Override
    public void insertFavClick(Meal meal) {
        homePresenter.insertFavMeal(meal);
    }


}