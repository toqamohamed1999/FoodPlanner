package eg.gov.iti.jets.foodplanner.plan.presenter;

import static android.content.ContentValues.TAG;

import android.util.Log;

import eg.gov.iti.jets.foodplanner.MealDetails.view.MealDetailsViewInterface;
import eg.gov.iti.jets.foodplanner.model.Meal;
import eg.gov.iti.jets.foodplanner.model.PlanMeal;
import eg.gov.iti.jets.foodplanner.model.RepositoryInterface;
import eg.gov.iti.jets.foodplanner.plan.view.PlanViewInterface;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PlanPresenter implements  PlanPresenterInterface{

    private RepositoryInterface repositoryInterface;
    PlanViewInterface planViewInterface;

    public PlanPresenter(PlanViewInterface planViewInterface, RepositoryInterface repositoryInterface) {
        this.planViewInterface = planViewInterface;
        this.repositoryInterface = repositoryInterface;
    }

    @Override
    public void deletePlanMeal(PlanMeal meal) {
        repositoryInterface.deletePlanMeal(meal);
    }
//    @Override
//    public void getStoredFavMeals() {
//        repositoryInterface.getStoreFavMeals().subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(meals -> favViewInterface.getStoredFavMeals(meals),
//                        error-> Log.i(TAG, "getStoredFavMeals: "+error));
//
//    }

    @Override
    public void getStoredPlanMeals(String day) {
        repositoryInterface.getStoredPlanMeals(day);
    }


}
