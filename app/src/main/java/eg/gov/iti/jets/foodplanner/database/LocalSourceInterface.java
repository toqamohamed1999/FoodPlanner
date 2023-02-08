package eg.gov.iti.jets.foodplanner.database;

import androidx.lifecycle.LiveData;

import java.util.List;

import eg.gov.iti.jets.foodplanner.model.Meal;
import eg.gov.iti.jets.foodplanner.model.PlanMeal;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public interface LocalSourceInterface {

    Observable<List<Meal>> getStoredMeals();

    void insertMeal(Meal meal);

    void deleteMeal(Meal meal);

    void deleteMealTable();

    Observable<List<PlanMeal>> getStoredPlanMeals();

    void insertPlanMeal(PlanMeal planMeal);

    void deletePlanMeal(PlanMeal planMeal);

    void deletePlanMealTable();
}
