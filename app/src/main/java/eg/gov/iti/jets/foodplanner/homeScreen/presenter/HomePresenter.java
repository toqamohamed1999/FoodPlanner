package eg.gov.iti.jets.foodplanner.homeScreen.presenter;

import android.util.Log;

import java.util.List;

import eg.gov.iti.jets.foodplanner.model.Meal;
import eg.gov.iti.jets.foodplanner.homeScreen.view.HomeViewInterface;
import eg.gov.iti.jets.foodplanner.model.MealRoot;
import eg.gov.iti.jets.foodplanner.model.RepositoryInterface;
import eg.gov.iti.jets.foodplanner.network.NetworkDelegate;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
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
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mealRoot1 -> homeViewInterface.getRandomMeal(mealRoot1.getMeals().get(0)),
                        error -> Log.i(TAG, "getRandomMeal: "+error));
    }

    @Override
    public void getEgyptianMeals(Single<MealRoot> mealRoot) {
        mealRoot.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mealRoot1 -> homeViewInterface.getEgyptianMeals(mealRoot1.getMeals()),
                        error -> Log.i(TAG, "getEgyptianMeals: "+error));
    }

    @Override
    public void getMealByName(Single<MealRoot> mealRoot) {
        mealRoot.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mealRoot1 -> homeViewInterface.getMealById(mealRoot1.getMeals().get(0)),
                        error -> Log.i(TAG, "getMealByName: "+error));
    }




//    @Override
//    public void getRandomMeal() {
//         repositoryInterface.getRandomMeal(this);
//    }
//
//    @Override
//    public void getEgyptianMeals() {
//        repositoryInterface.getEgyptianMeals(this);
//    }
//
//    @Override
//    public void getMealById() {
//        repositoryInterface.getMealById(this);
//    }
//
//    @Override
//    public void onSuccessGetRandomMeal(Meal meal) {
//        homeViewInterface.getRandomMeal(meal);
//    }
//
//    @Override
//    public void onFailGetRandomMeal(String error) {
//        Log.i(TAG, "onFailGetRandomMeal: "+error);
//    }
//
//    @Override
//    public void onSuccessGetEgyptianMeals(List<Meal> mealsList) {
//        homeViewInterface.getEgyptianMeals(mealsList);
//    }
//
//    @Override
//    public void onFailGetEgyptianMeals(String error) {
//        Log.i(TAG, "onFailGetEgyptianMeals: "+error);
//    }
//
//    @Override
//    public void onSuccessGetMealByName(Meal meal) {
//        homeViewInterface.getMealById(meal);
//    }
//
//    @Override
//    public void onFailGetMealByName(String error) {
//        Log.i(TAG, "onFailGetMealByName: "+error);
//    }
}
