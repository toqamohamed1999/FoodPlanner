package eg.gov.iti.jets.foodplanner.searchResult.presenter;

import android.util.Log;

import java.util.List;

import eg.gov.iti.jets.foodplanner.model.Meal;
import eg.gov.iti.jets.foodplanner.model.RepositoryInterface;
import eg.gov.iti.jets.foodplanner.network.NetworkDelegateSearchResult;
import eg.gov.iti.jets.foodplanner.searchResult.view.SearchResultViewInterface;


public class SearchResultPresenter implements SearchResultPresenterInterface, NetworkDelegateSearchResult{

    private static final String TAG = "SearchResultPresenter";

    SearchResultViewInterface searchResultViewInterface;
    RepositoryInterface repositoryInterface;

    public SearchResultPresenter(SearchResultViewInterface searchResultViewInterface, RepositoryInterface repositoryInterface) {
        this.searchResultViewInterface = searchResultViewInterface;
        this.repositoryInterface = repositoryInterface;
    }

    @Override
    public void getSpecificCategoryMeals(String category) {
        repositoryInterface.getSpecificCategoryMeals(this,category);
    }

    @Override
    public void getSpecificCountryMeals(String country) {
        repositoryInterface.getSpecificCountryMeals(this,country);
    }

    @Override
    public void onSuccessGetSpecificCategoryMeals(List<Meal> mealsList) {
        searchResultViewInterface.getSpecificCategoryMeals(mealsList);
    }

    @Override
    public void onFailGetSpecificCategoryMeals(String error) {
        Log.i(TAG, "onFailGetSpecificCategoryMeals: "+error);
    }

    @Override
    public void onSuccessGetSpecificCountryMeals(List<Meal> mealsList) {
        searchResultViewInterface.getSpecificCountryMeals(mealsList);
    }

    @Override
    public void onFailGetSpecificCountryMeals(String error) {
        Log.i(TAG, "onFailGetSpecificCountryMeals: "+error);
    }
}
