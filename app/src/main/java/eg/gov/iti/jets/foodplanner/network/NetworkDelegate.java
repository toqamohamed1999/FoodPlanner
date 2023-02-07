package eg.gov.iti.jets.foodplanner.network;

import java.util.List;

import eg.gov.iti.jets.foodplanner.model.Meal;

public interface NetworkDelegate {

    public void onSuccessGetRandomMeal(Meal meal);
    public void onFailGetRandomMeal(String error);

    public void onSuccessGetEgyptianMeals(List<Meal> mealsList);
    public void onFailGetEgyptianMeals(String error);

    public void onSuccessGetMealByName(Meal meal);
    public void onFailGetMealByName(String error);
}
