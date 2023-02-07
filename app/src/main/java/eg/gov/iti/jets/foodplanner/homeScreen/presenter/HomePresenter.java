package eg.gov.iti.jets.foodplanner.homeScreen.presenter;

import android.util.Log;

import java.util.List;

import eg.gov.iti.jets.foodplanner.model.Meal;
import eg.gov.iti.jets.foodplanner.homeScreen.view.HomeViewInterface;
import eg.gov.iti.jets.foodplanner.model.RepositoryInterface;
import eg.gov.iti.jets.foodplanner.network.NetworkDelegate;

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
    public void onSuccessGetRandomMeal(Meal meal) {
        homeViewInterface.getRandomMeal(meal);
    }

    @Override
    public void onFailGetRandomMeal(String error) {
        Log.i(TAG, "onFailGetRandomMeal: "+error);
    }

    @Override
    public void onSuccessGetEgyptianMeals(List<Meal> mealsList) {
        homeViewInterface.getEgyptianMeals(mealsList);
    }

    @Override
    public void onFailGetEgyptianMeals(String error) {
        Log.i(TAG, "onFailGetEgyptianMeals: "+error);
    }

    @Override
    public void onSuccessGetMealByName(Meal meal) {
        homeViewInterface.getMealById(meal);
    }

    @Override
    public void onFailGetMealByName(String error) {
        Log.i(TAG, "onFailGetMealByName: "+error);
    }
}
