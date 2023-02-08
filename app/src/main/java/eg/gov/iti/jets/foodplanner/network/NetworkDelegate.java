package eg.gov.iti.jets.foodplanner.network;

import java.util.List;

import eg.gov.iti.jets.foodplanner.model.Meal;
import eg.gov.iti.jets.foodplanner.model.MealRoot;
import io.reactivex.rxjava3.core.Single;

public interface NetworkDelegate {

    public void getRandomMeal(Single<MealRoot> mealRoot);

    public void getEgyptianMeals(Single<MealRoot> mealRoot);

    public void getMealByName(Single<MealRoot> mealRoot);

}
