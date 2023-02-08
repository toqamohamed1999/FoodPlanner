package eg.gov.iti.jets.foodplanner.search.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import eg.gov.iti.jets.foodplanner.IngredientsAdapter;
import eg.gov.iti.jets.foodplanner.searchBy.view.OnSearchingActivity;
import eg.gov.iti.jets.foodplanner.R;
import eg.gov.iti.jets.foodplanner.database.LocalSource;
import eg.gov.iti.jets.foodplanner.model.Category;
import eg.gov.iti.jets.foodplanner.model.Country;
import eg.gov.iti.jets.foodplanner.model.Ingredient;
import eg.gov.iti.jets.foodplanner.model.Repo;
import eg.gov.iti.jets.foodplanner.network.RemoteSource;
import eg.gov.iti.jets.foodplanner.search.presenter.SearchFragmentPresenter;


public class SearchFragment extends Fragment implements SearchFragmentViewInterFace{

    private static final String TAG = "SearchFragment";

    public static final String CATEGORY_KEY = "CategoryName";

    public static final String COUNTRY_KEY = "CountryName";
    private RecyclerView categoryRecyclerView,countryRecyclerView,ingredientRecyclerView;

    private ArrayList<Category> categoriesList = new ArrayList<>();
    private ArrayList<Ingredient> ingredientsList = new ArrayList<>();
    private ArrayList<Country> countryList = new ArrayList<>();

    CategoryAdapter categoryAdapter ;
    IngredientsAdapter ingredientsAdapter;

    CountryAdapter countryAdapter;

    SearchView searchView;

    SearchFragmentPresenter searchFragmentPresenter;

    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUI(view);
        setupRecyclerView();
        setUpPresenter();
    }

    private void initUI(View view){
        categoryRecyclerView = view.findViewById(R.id.search_category_recyclerView);
        countryRecyclerView = view.findViewById(R.id.search_country_recyclerView);
        searchView = view.findViewById(R.id.searchFragment_searchView);
        ingredientRecyclerView=view.findViewById(R.id.search_ing_recyclerView);

        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                startActivity(new Intent(requireContext(), OnSearchingActivity.class));
            }
        });

    }
    private void setupRecyclerView() {
        RecyclerView.LayoutManager categoryLayoutManager = new GridLayoutManager(requireContext(), 2);
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        categoryRecyclerView.setLayoutManager(categoryLayoutManager);
        categoryAdapter = new CategoryAdapter(requireContext(), categoriesList);
        categoryRecyclerView.setAdapter(categoryAdapter);

        RecyclerView.LayoutManager countryLayoutManager = new GridLayoutManager(requireContext(), 2);
        countryRecyclerView.setLayoutManager(countryLayoutManager);
        countryAdapter = new CountryAdapter(requireContext(), countryList);
        countryRecyclerView.setAdapter(countryAdapter);


//        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        ingredientRecyclerView.setLayoutManager(layoutManager);
//        ingredientsAdapter = new IngredientsAdapter(requireContext(), ingredientsList);
//        ingredientRecyclerView.setAdapter(ingredientsAdapter);
    }

    void setUpPresenter(){
        searchFragmentPresenter = new SearchFragmentPresenter(this,Repo.getInstance(requireContext(), LocalSource.getLocalSource(requireContext()), RemoteSource.getRemoteSource()));
        searchFragmentPresenter.getAllCategories();
        searchFragmentPresenter.getAllCountries();
    }


    @Override
    public void getAllCategories(List<Category> categoriesList) {
        categoryAdapter.setData(categoriesList);
    }

    @Override
    public void getAllCountry(List<Country> countriesList) {
        countryAdapter.setData(countriesList);
    }
}