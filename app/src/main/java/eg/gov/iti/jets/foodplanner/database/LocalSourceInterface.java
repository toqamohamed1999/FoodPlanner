package eg.gov.iti.jets.foodplanner.database;

import androidx.lifecycle.LiveData;

import java.util.List;

import eg.gov.iti.jets.foodplanner.MealDetails.view.CheckFavInterface;
import eg.gov.iti.jets.foodplanner.model.Meal;
import eg.gov.iti.jets.foodplanner.model.PlanMeal;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public interface LocalSourceInterface {

    Flowable<List<Meal>> getStoredMeals();

    void insertMeal(Meal meal);

    void deleteMeal(Meal meal);

    void deleteMealTable();

    Flowable<List<PlanMeal>> getStoredPlanMeals(String day);
    Flowable<List<PlanMeal>> getStoredPlanMeals();

    void insertPlanMeal(PlanMeal planMeal);

    void deletePlanMeal(PlanMeal planMeal);

    void deletePlanMealTable();
    void MealIsExistInFav(CheckFavInterface checkFavInterface,String idMeal);
}
