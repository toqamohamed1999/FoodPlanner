package eg.gov.iti.jets.foodplanner.MealDetails.presenter;

import static android.content.ContentValues.TAG;

import android.util.Log;

import eg.gov.iti.jets.foodplanner.MealDetails.view.CheckFavInterface;
import eg.gov.iti.jets.foodplanner.MealDetails.view.MealDetailsViewInterface;
import eg.gov.iti.jets.foodplanner.homeScreen.view.HomeViewInterface;
import eg.gov.iti.jets.foodplanner.model.Meal;
import eg.gov.iti.jets.foodplanner.model.MealRoot;
import eg.gov.iti.jets.foodplanner.model.PlanMeal;
import eg.gov.iti.jets.foodplanner.model.RepositoryInterface;
import eg.gov.iti.jets.foodplanner.network.NetworkDelegateDetails;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealDetailsPresenter implements MealDetailsPresenterInterface , NetworkDelegateDetails, CheckFavInterface {

    MealDetailsViewInterface mealDetailsViewInterface;

    private RepositoryInterface repositoryInterface;

    public MealDetailsPresenter(MealDetailsViewInterface mealDetailsViewInterface, RepositoryInterface repositoryInterface) {
        this.mealDetailsViewInterface = mealDetailsViewInterface;
        this.repositoryInterface = repositoryInterface;
    }

    @Override
    public void insertFavMeal(Meal meal) {
        repositoryInterface.insertFavMeal(meal);
    }

    @Override
    public void deleteFavMeal(Meal meal) {
        repositoryInterface.deleteFavMeal(meal);
    }

    @Override
    public void MealIsExistInFav(String idMeal) {
        repositoryInterface.MealIsExistInFav(this,idMeal);
    }

    @Override
    public void isFav(boolean isFav) {
        mealDetailsViewInterface.checkMealIsFav(isFav);
    }


    @Override
    public void insertPlanMeal(PlanMeal meal) {
        repositoryInterface.insertPlanMeal(meal);
    }

    @Override
    public void getMealDetailsById(Single<MealRoot> mealRoot) {
        mealRoot.subscribeOn(Schedulers.io())
                .filter(mealRoot1 -> mealRoot1 != null && mealRoot1.getMeals().size() > 0)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mealRoot1 -> mealDetailsViewInterface.getMealDetailsById(mealRoot1.getMeals().get(0)),
                        error -> Log.i(TAG, "getMealDetailsById: " + error));
    }

    @Override
    public void getMealDetailsById(String idMeal) {
        repositoryInterface.getMealById(this, idMeal);
    }

}
