package eg.gov.iti.jets.foodplanner.searchResult.presenter;

import android.util.Log;

import java.util.List;

import eg.gov.iti.jets.foodplanner.model.Meal;
import eg.gov.iti.jets.foodplanner.model.MealRoot;
import eg.gov.iti.jets.foodplanner.model.RepositoryInterface;
import eg.gov.iti.jets.foodplanner.network.NetworkDelegateSearchResult;
import eg.gov.iti.jets.foodplanner.searchResult.view.SearchResultViewInterface;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class SearchResultPresenter implements SearchResultPresenterInterface, NetworkDelegateSearchResult{

    private static final String TAG = "SearchResultPresenter";

    SearchResultViewInterface searchResultViewInterface;
    RepositoryInterface repositoryInterface;

    public SearchResultPresenter(SearchResultViewInterface searchResultViewInterface, RepositoryInterface repositoryInterface) {
        this.searchResultViewInterface = searchResultViewInterface;
        this.repositoryInterface = repositoryInterface;
    }

    @Override
    public void getSpecificCategoryMeals(Single<MealRoot> mealRoot) {
        mealRoot.subscribeOn(Schedulers.io())
                .filter(mealRoot1 -> mealRoot1!=null && mealRoot1.getMeals().size() > 0)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mealRoot1 -> searchResultViewInterface.getSpecificCategoryMeals(mealRoot1.getMeals()),
                        error -> Log.i(TAG, "getSpecificCategoryMeals: "+error));
    }

    @Override
    public void getSpecificCountryMeals(Single<MealRoot> mealRoot) {
        mealRoot.subscribeOn(Schedulers.io())
                .filter(mealRoot1 -> mealRoot1!=null && mealRoot1.getMeals().size() > 0)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mealRoot1 -> searchResultViewInterface.getSpecificCountryMeals(mealRoot1.getMeals()),
                        error -> Log.i(TAG, "getSpecificCountryMeals: "+error));
    }

    @Override
    public void getMealByName(Single<MealRoot> mealRoot) {
        mealRoot.subscribeOn(Schedulers.io())
                .filter(mealRoot1 -> mealRoot1!=null && mealRoot1.getMeals().size() > 0)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mealRoot1 -> searchResultViewInterface.getMealByName(mealRoot1.getMeals()),
                        error -> Log.i(TAG, "getMealByName: "+error));
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
    public void getMealByName(String Name) {
        repositoryInterface.getMealByName(this,Name);
    }


}
