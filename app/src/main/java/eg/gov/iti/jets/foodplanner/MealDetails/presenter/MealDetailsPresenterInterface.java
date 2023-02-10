package eg.gov.iti.jets.foodplanner.MealDetails.presenter;

import eg.gov.iti.jets.foodplanner.model.Meal;
import eg.gov.iti.jets.foodplanner.model.PlanMeal;

public interface MealDetailsPresenterInterface {
    public void insertFavMeal(Meal meal);
    public  void deleteFavMeal(Meal meal);
    public  Boolean MealIsExistInFav(String idMeal);
    public void insertPlanMeal(PlanMeal meal);

    public void getMealDetailsById(String idMeal);
}
