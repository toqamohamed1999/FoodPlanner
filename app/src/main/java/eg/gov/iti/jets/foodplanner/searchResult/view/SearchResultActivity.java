package eg.gov.iti.jets.foodplanner.searchResult.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import eg.gov.iti.jets.foodplanner.MealAdapter;
import eg.gov.iti.jets.foodplanner.R;
import eg.gov.iti.jets.foodplanner.database.LocalSource;
import eg.gov.iti.jets.foodplanner.model.Meal;
import eg.gov.iti.jets.foodplanner.model.Repo;
import eg.gov.iti.jets.foodplanner.network.ApiClient;
import eg.gov.iti.jets.foodplanner.network.RemoteSource;
import eg.gov.iti.jets.foodplanner.search.view.SearchFragment;
import eg.gov.iti.jets.foodplanner.searchResult.presenter.SearchResultPresenter;

public class SearchResultActivity extends AppCompatActivity implements SearchResultViewInterface{

    private static final String TAG = "SearchResultActivity";

    private RecyclerView recyclerView;

    private List<Meal> mealsList = new ArrayList<>();

    private MealAdapter mealAdapter ;

    private SearchResultPresenter searchResultPresenter;

    private SearchView searchView;

    private TextView titleTextView;

    ImageButton backImageButton;

    String category,country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        getSupportActionBar().hide();

        initUI();
        setupRecyclerView();
        getMyIntent();
        setUpPresenter();

    }

    private void initUI(){
        recyclerView = findViewById(R.id.resultSearch_recyclerView);
        searchView = findViewById(R.id.searchResult_searchView);
        titleTextView = findViewById(R.id.resultSearch_title_textView);
        backImageButton = findViewById(R.id.resultSearch_back_imageBtn);
    }

    void getMyIntent(){

        Intent intent = getIntent();
        if(intent.getStringExtra(SearchFragment.CATEGORY_KEY) != null){
            category = intent.getStringExtra(SearchFragment.CATEGORY_KEY);
            titleTextView.setText(category);
        }if(intent.getStringExtra(SearchFragment.COUNTRY_KEY) != null){
            country = intent.getStringExtra(SearchFragment.COUNTRY_KEY);
            titleTextView.setText(country);
        }

    }



    private void setupRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(SearchResultActivity.this, 2);
        recyclerView.setLayoutManager(layoutManager);
        mealAdapter = new MealAdapter(SearchResultActivity.this, mealsList);
        recyclerView.setAdapter(mealAdapter);
    }

    void setUpPresenter(){
        searchResultPresenter = new SearchResultPresenter(this,Repo.getInstance(getApplicationContext(), LocalSource.getLocalSource(getApplicationContext()), RemoteSource.getRemoteSource()));
        if(category != null) searchResultPresenter.getSpecificCategoryMeals(category);
        if(country != null) searchResultPresenter.getSpecificCountryMeals(country);
    }


    @Override
    public void getSpecificCategoryMeals(List<Meal> mealsList) {
        mealAdapter.setData(mealsList);
    }

    @Override
    public void getSpecificCountryMeals(List<Meal> mealsList) {
        mealAdapter.setData(mealsList);
    }
}