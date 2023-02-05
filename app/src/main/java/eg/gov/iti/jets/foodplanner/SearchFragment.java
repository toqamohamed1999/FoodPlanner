package eg.gov.iti.jets.foodplanner;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class SearchFragment extends Fragment {

    private static final String TAG = "SearchFragment";

    private RecyclerView categoryRecyclerView,ingredientRecyclerView;

    private ArrayList<Category> categoriesList = new ArrayList<>();
    private ArrayList<Ingredient> ingredientsList = new ArrayList<>();

    CategoryAdapter categoryAdapter ;
    IngredientsAdapter ingredientsAdapter;

    SearchView searchView;

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
        categoryRecyclerView = view.findViewById(R.id.search_category_recyclerView);
        searchView = view.findViewById(R.id.searchFragment_searchView);
        ingredientRecyclerView=view.findViewById(R.id.search_ing_recyclerView);

        fillData();
        setupRecyclerView();

        searchView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                startActivity(new Intent(requireContext(),OnSearchingActivity.class));
            }
        });
    }

    private void setupRecyclerView() {
        //RecyclerView.LayoutManager layoutManager = new GridLayoutManager(requireContext(), 2);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(linearLayoutManager);
        categoryAdapter = new CategoryAdapter(requireContext(), categoriesList);
        categoryRecyclerView.setAdapter(categoryAdapter);

        LinearLayoutManager linearLayoutManagerIng = new LinearLayoutManager(requireContext());
        linearLayoutManagerIng.setOrientation(RecyclerView.HORIZONTAL);
        ingredientRecyclerView.setLayoutManager(linearLayoutManagerIng);
        ingredientsAdapter = new IngredientsAdapter(requireContext(), ingredientsList);
        ingredientRecyclerView.setAdapter(ingredientsAdapter);
    }

    private void fillData(){
        categoriesList.add(new Category("1","Beef","https://www.themealdb.com/images/category/beef.png",""));
        categoriesList.add(new Category("2","Chicken","https://www.themealdb.com/images/category/chicken.png",""));
        categoriesList.add(new Category("1","Beef","https://www.themealdb.com/images/category/beef.png",""));
        categoriesList.add(new Category("2","Chicken","https://www.themealdb.com/images/category/chicken.png",""));
        categoriesList.add(new Category("1","Beef","https://www.themealdb.com/images/category/beef.png",""));
        categoriesList.add(new Category("2","Chicken","https://www.themealdb.com/images/category/chicken.png",""));

        ingredientsList.add(new Ingredient("1","Beef","https://www.themealdb.com/images/category/beef.png"));
        ingredientsList.add(new Ingredient("2","Chicken","https://www.themealdb.com/images/category/chicken.png"));
        ingredientsList.add(new Ingredient("1","Beef","https://www.themealdb.com/images/category/beef.png"));
        ingredientsList.add(new Ingredient("2","Chicken","https://www.themealdb.com/images/category/chicken.png"));
        ingredientsList.add(new Ingredient("1","Beef","https://www.themealdb.com/images/category/beef.png"));

    }


}