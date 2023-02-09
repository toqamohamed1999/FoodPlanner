package eg.gov.iti.jets.foodplanner.homeScreen.presenter;

import android.util.Log;

import java.util.List;

import eg.gov.iti.jets.foodplanner.model.Meal;
import eg.gov.iti.jets.foodplanner.homeScreen.view.HomeViewInterface;
import eg.gov.iti.jets.foodplanner.model.MealRoot;
import eg.gov.iti.jets.foodplanner.model.RepositoryInterface;
import eg.gov.iti.jets.foodplanner.network.NetworkDelegate;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePresenter implements HomePresenterInterface, NetworkDelegate {

    private static final String TAG = "HomePresenter";

    private HomeViewInterface homeViewInterface;
    private RepositoryInterface repositoryInterface;

    public HomePresenter(HomeViewInterface homeViewInterface, RepositoryInterface repositoryInterface) {
        this.homeViewInterface = homeViewInterface;
        this.repositoryInterface = repositoryInterface;
    }


    @Override
    public void getRandomMeal() {
        repositoryInterface.getRandomMeal(this);
    }

    @Override
    public void getEgyptianMeals() {
        repositoryInterface.getEgyptianMeals(this);
    }

    @Override
    public void getMealById() {
        repositoryInterface.getMealById(this);
    }




    @Override
    public void getRandomMeal(Single<MealRoot> mealRoot) {

        mealRoot.subscribeOn(Schedulers.io())
                .filter(mealRoot1 -> mealRoot1!=null && mealRoot1.getMeals().size() > 0)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mealRoot1 -> homeViewInterface.getRandomMeal(mealRoot1.getMeals().get(0)),
                        error -> Log.i(TAG, "getRandomMeal: "+error));
    }

    @Override
    public void getEgyptianMeals(Single<MealRoot> mealRoot) {

        Observable observable = mealRoot.toObservable();

         mealRoot.toObservable().subscribeOn(Schedulers.io())
                .filter(mealRoot1 -> mealRoot1!=null && mealRoot1.getMeals().size() > 0)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mealRoot1 -> homeViewInterface.getEgyptianMeals(mealRoot1.getMeals()),
                        error -> Log.i(TAG, "getEgyptianMeals: "+error));



//        mealRoot.subscribeOn(Schedulers.io())
//                .filter(mealRoot1 -> mealRoot1!=null && mealRoot1.getMeals().size() > 0)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(mealRoot1 -> homeViewInterface.getEgyptianMeals(mealRoot1.getMeals()),
//                        error -> Log.i(TAG, "getEgyptianMeals: "+error));
    }

    @Override
    public void getMealByName(Single<MealRoot> mealRoot) {
        mealRoot.subscribeOn(Schedulers.io())
                .filter(mealRoot1 -> mealRoot1!=null && mealRoot1.getMeals().size() > 0)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mealRoot1 -> homeViewInterface.getMealById(mealRoot1.getMeals().get(0)),
                        error -> Log.i(TAG, "getMealByName: "+error));
    }

    @Override
    public void insertFavMeal(Meal meal) {
        repositoryInterface.insertFavMeal(meal);
    }


}
