package eg.gov.iti.jets.foodplanner.MealDetails.view;

import eg.gov.iti.jets.foodplanner.model.Meal;

public interface MealDetailsViewInterface {

    public void getMealDetailsById(Meal meal);

    public void checkMealIsFav(boolean isFav);
}
