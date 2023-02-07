package eg.gov.iti.jets.foodplanner.homeScreen.view;

import java.util.List;

import eg.gov.iti.jets.foodplanner.model.Meal;

public interface HomeViewInterface {

    public void getRandomMeal(Meal meal);

    public void getEgyptianMeals(List<Meal> mealsList);

    public void getMealById(Meal meal);

}
