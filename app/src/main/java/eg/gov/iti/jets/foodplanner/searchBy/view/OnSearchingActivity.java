package eg.gov.iti.jets.foodplanner.searchBy.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import androidx.appcompat.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import eg.gov.iti.jets.foodplanner.MealAdapter;
import eg.gov.iti.jets.foodplanner.R;
import eg.gov.iti.jets.foodplanner.database.LocalSource;
import eg.gov.iti.jets.foodplanner.model.Meal;
import eg.gov.iti.jets.foodplanner.model.Repo;
import eg.gov.iti.jets.foodplanner.network.RemoteSource;
import eg.gov.iti.jets.foodplanner.searchBy.presenter.OnSearchingPresenter;
import eg.gov.iti.jets.foodplanner.searchResult.presenter.SearchResultPresenter;
import eg.gov.iti.jets.foodplanner.searchResult.view.SearchResultActivity;

public class OnSearchingActivity extends AppCompatActivity implements OnSearchingViewInterface{

    private static final String TAG = "OnSearchingActivity";

    private SearchView searchView;

    private RecyclerView recyclerView;

    private MealAdapter mealAdapter;

    private List<Meal> mealsList = new ArrayList<>();

    private ImageButton backImageButton;

    AutoCompleteTextView autoCompleteTextView;

    private ArrayAdapter<String> arrayAdapter;

    private OnSearchingPresenter onSearchingPresenter;

    private static String selectedSearchBy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_searching);
        getSupportActionBar().hide();

        initUI();
        setupRecyclerView();
        setUpPresenter();
        setUpAutoCompleteTv();
        handelSearchViewEvent();
    }

    private void initUI() {
        recyclerView = findViewById(R.id.onSearching_recyclerView);
        searchView = findViewById(R.id.onSearching_searchView);
        backImageButton = findViewById(R.id.onSearching_back_imageBtn);
        autoCompleteTextView = findViewById(R.id.onSearching_autoCompleteTextView);
    }

    void setUpPresenter(){
        onSearchingPresenter = new OnSearchingPresenter(this, Repo.getInstance(getApplicationContext(), LocalSource.getLocalSource(getApplicationContext()), RemoteSource.getRemoteSource()));
    }
    private void setupRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(OnSearchingActivity.this, 2);
        recyclerView.setLayoutManager(layoutManager);
        mealAdapter = new MealAdapter(OnSearchingActivity.this, mealsList);
        recyclerView.setAdapter(mealAdapter);
    }


    private void setUpAutoCompleteTv() {
        ArrayList<String> searchByList = new ArrayList<>();
        searchByList.add("Meal name");
        searchByList.add("Category");
        searchByList.add("Country");
        searchByList.add("Ingredient");

        arrayAdapter = new ArrayAdapter<>(OnSearchingActivity.this, android.R.layout.simple_list_item_1, searchByList);
        autoCompleteTextView.setAdapter(arrayAdapter);
        autoCompleteTextView.setText(arrayAdapter.getItem(0),false);
        selectedSearchBy = searchByList.get(0);

        setOnSelectFilterEvent();
    }

    private void setOnSelectFilterEvent(){
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedSearchBy = parent.getItemAtPosition(position).toString();
            }
        });
    }

    private void handelSearchViewEvent(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                handlePresenterCall(newText);
                Log.i(TAG, "onQueryTextChange: "+selectedSearchBy);
                return true;
            }
        });
    }

     private void handlePresenterCall(String text){
         switch (selectedSearchBy){
             case "Country":
                 onSearchingPresenter.getMealsBYCountry(text);
                 break;
             case "Ingredient":
                 onSearchingPresenter.getMealsByIngredients(text);
                 break;
             case "Category":
                 onSearchingPresenter.getMealsByCategory(text);
                 break;
             case "Meal name":
                 onSearchingPresenter.getMealByName(text);
                 break;
             default:
                 onSearchingPresenter.getMealByName(text);
         }
    }

    @Override
    public void getMealsByCountry(List<Meal> mealsList) { mealAdapter.setData(mealsList);
    }

    @Override
    public void getMealsByIngredient(List<Meal> mealsList) {
        mealAdapter.setData(mealsList);
    }

    @Override
    public void getMealsByCategory(List<Meal> mealsList) {
        mealAdapter.setData(mealsList);
    }

    @Override
    public void getMealsByFirstletter(List<Meal> mealsList) {
        mealAdapter.setData(mealsList);

    }

    @Override
    public void getMealByName(List<Meal> mealsList) {
        mealAdapter.setData(mealsList);

    }
}