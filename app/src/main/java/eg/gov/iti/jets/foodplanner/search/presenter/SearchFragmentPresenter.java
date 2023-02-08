package eg.gov.iti.jets.foodplanner.search.presenter;

import android.util.Log;

import java.util.List;

import eg.gov.iti.jets.foodplanner.homeScreen.view.HomeViewInterface;
import eg.gov.iti.jets.foodplanner.model.Category;
import eg.gov.iti.jets.foodplanner.model.CategoryRoot;
import eg.gov.iti.jets.foodplanner.model.Country;
import eg.gov.iti.jets.foodplanner.model.CountryRoot;
import eg.gov.iti.jets.foodplanner.model.RepositoryInterface;
import eg.gov.iti.jets.foodplanner.network.NetworkDelegate;
import eg.gov.iti.jets.foodplanner.network.NetworkDelegateSearch;
import eg.gov.iti.jets.foodplanner.search.view.SearchFragmentViewInterFace;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchFragmentPresenter implements SearchFragmentPresenterInterFace, NetworkDelegateSearch {

    private static final String TAG = "SearchFragmentPresenter";
    private SearchFragmentViewInterFace searchFragmentViewInterFace;
    private RepositoryInterface repositoryInterface;

    public SearchFragmentPresenter(SearchFragmentViewInterFace searchFragmentViewInterFace, RepositoryInterface repositoryInterface) {
        this.searchFragmentViewInterFace = searchFragmentViewInterFace;
        this.repositoryInterface = repositoryInterface;
    }

    @Override
    public void getAllCategories(Single<CategoryRoot> categoryRoot) {

        categoryRoot.subscribeOn(Schedulers.io())
                .filter(categoryRoot1 -> categoryRoot1!=null && categoryRoot1.getCategories().size() > 0)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(categoryRoot1 -> searchFragmentViewInterFace.getAllCategories(categoryRoot1.getCategories()),
                        error -> Log.i(TAG, "getAllCategories: "+error));
    }

    @Override
    public void getAllCountries(Single<CountryRoot> countryRoot) {
        countryRoot.subscribeOn(Schedulers.io())
                .filter(countryRoot1 -> countryRoot1!=null && countryRoot1.getCountries().size() > 0)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(countryRoot1 -> searchFragmentViewInterFace.getAllCountry(countryRoot1.getCountries()),
                        error -> Log.i(TAG, "getAllCountries: "+error));
    }

    @Override
    public void getAllCategories() {
       repositoryInterface.getAllCategories(this);
    }

    @Override
    public void getAllCountries() {
        repositoryInterface.getAllCountries(this);
    }

}
