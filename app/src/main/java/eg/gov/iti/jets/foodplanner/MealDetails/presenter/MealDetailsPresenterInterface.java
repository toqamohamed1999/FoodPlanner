package eg.gov.iti.jets.foodplanner.MealDetails.presenter;

import eg.gov.iti.jets.foodplanner.model.Meal;

public interface MealDetailsPresenterInterface {
    public void insertFavMeal(Meal meal);
    public  void deleteFavMeal(Meal meal);
    public  Boolean MealIsExistInFav(String idMeal);
}
