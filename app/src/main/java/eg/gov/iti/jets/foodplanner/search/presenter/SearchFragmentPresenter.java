package eg.gov.iti.jets.foodplanner.search.presenter;

import android.util.Log;

import java.util.List;

import eg.gov.iti.jets.foodplanner.homeScreen.view.HomeViewInterface;
import eg.gov.iti.jets.foodplanner.model.Category;
import eg.gov.iti.jets.foodplanner.model.Country;
import eg.gov.iti.jets.foodplanner.model.RepositoryInterface;
import eg.gov.iti.jets.foodplanner.network.NetworkDelegate;
import eg.gov.iti.jets.foodplanner.network.NetworkDelegateSearch;
import eg.gov.iti.jets.foodplanner.search.view.SearchFragmentViewInterFace;

public class SearchFragmentPresenter implements SearchFragmentPresenterInterFace, NetworkDelegateSearch {

    private static final String TAG = "SearchFragmentPresenter";
    private SearchFragmentViewInterFace searchFragmentViewInterFace;
    private RepositoryInterface repositoryInterface;

    public SearchFragmentPresenter(SearchFragmentViewInterFace searchFragmentViewInterFace, RepositoryInterface repositoryInterface) {
        this.searchFragmentViewInterFace = searchFragmentViewInterFace;
        this.repositoryInterface = repositoryInterface;
    }


    @Override
    public void getAllCategories() {
        repositoryInterface.getAllCategories(this);
    }

    @Override
    public void getAllCountries() {
          repositoryInterface.getAllCountries(this);
    }

    @Override
    public void onSuccessGetAllCategories(List<Category> categoriesList) {
          searchFragmentViewInterFace.getAllCategories(categoriesList);
    }

    @Override
    public void onFailGetAllCategories(String error) {
        Log.i(TAG, "onFailGetAllCategories: "+error);
    }

    @Override
    public void onSuccessGetAllCountries(List<Country> countriesList) {
        searchFragmentViewInterFace.getAllCountry(countriesList);
    }

    @Override
    public void onFailGetAllCountries(String error) {
        Log.i(TAG, "onFailGetAllCategories: "+error);
    }
}
