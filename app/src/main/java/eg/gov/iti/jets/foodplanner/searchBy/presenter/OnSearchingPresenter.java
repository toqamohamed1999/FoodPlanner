package eg.gov.iti.jets.foodplanner.searchBy.presenter;

import android.util.Log;

import eg.gov.iti.jets.foodplanner.model.MealRoot;
import eg.gov.iti.jets.foodplanner.model.RepositoryInterface;
import eg.gov.iti.jets.foodplanner.network.NetworkDelegateOnSearching;
import eg.gov.iti.jets.foodplanner.searchBy.view.OnSearchingViewInterface;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class OnSearchingPresenter implements OnSearchingPresenterInterface, NetworkDelegateOnSearching {

    private static final String TAG = "OnSearchingPresenter";

    private OnSearchingViewInterface onSearchingViewInterface;

    private RepositoryInterface repositoryInterface;

    public OnSearchingPresenter(OnSearchingViewInterface onSearchingViewInterface, RepositoryInterface repositoryInterface) {
        this.onSearchingViewInterface = onSearchingViewInterface;
        this.repositoryInterface = repositoryInterface;
    }

    @Override
    public void getMealsByCountry(Single<MealRoot> mealRoot) {
        mealRoot.subscribeOn(Schedulers.io())
                .filter(mealRoot1 -> mealRoot1 != null && mealRoot1.getMeals().size() > 0)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mealRoot1 -> onSearchingViewInterface.getMealsByCountry(mealRoot1.getMeals()),
                        error -> Log.i(TAG, "getMealsByCountry: " + error));
    }

    @Override
    public void getMealsByIngredients(Single<MealRoot> mealRoot) {
        mealRoot.subscribeOn(Schedulers.io())
                .filter(mealRoot1 -> mealRoot1 != null && mealRoot1.getMeals().size() > 0)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mealRoot1 -> onSearchingViewInterface.getMealsByIngredient(mealRoot1.getMeals()),
                        error -> Log.i(TAG, "getMealsByIngredients: " + error));
    }

    @Override
    public void getMealsByCategory(Single<MealRoot> mealRoot) {
        mealRoot.subscribeOn(Schedulers.io())
                .filter(mealRoot1 -> mealRoot1 != null && mealRoot1.getMeals().size() > 0)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mealRoot1 -> onSearchingViewInterface.getMealsByCategory(mealRoot1.getMeals()),
                        error -> Log.i(TAG, "getMealsByCategory: " + error));
    }

    @Override
    public void getMealByName(Single<MealRoot> mealRoot) {
        mealRoot.subscribeOn(Schedulers.io())
                .filter(mealRoot1 -> mealRoot1 != null && mealRoot1.getMeals().size() > 0)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mealRoot1 -> onSearchingViewInterface.getMealByName(mealRoot1.getMeals()),
                        error -> Log.i(TAG, "getMealByName: " + error));
    }

    @Override
    public void getMealsByFirstLetter(Single<MealRoot> mealRoot) {
        mealRoot.subscribeOn(Schedulers.io())
                .filter(mealRoot1 -> mealRoot1 != null && mealRoot1.getMeals().size() > 0)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mealRoot1 -> onSearchingViewInterface.getMealsByFirstletter(mealRoot1.getMeals()),
                        error -> Log.i(TAG, "getMealsByFirstLetter: " + error));
    }

    @Override
    public void getMealsBYCountry(String country) {
        repositoryInterface.getMealsByCountry(this, country);
    }

    @Override
    public void getMealsByIngredients(String ingredient) {
        repositoryInterface.getMealsByIngredients(this, ingredient);
    }

    @Override
    public void getMealsByCategory(String category) {
        repositoryInterface.getMealsByCategory(this, category);
    }

    @Override
    public void getMealsByFirstLetter(String firstLetter) {
        repositoryInterface.getMealsByFirstLetter(this, firstLetter);
    }

    @Override
    public void getMealByName(String name) {
        repositoryInterface.getMealByName(this, name);
    }
}
