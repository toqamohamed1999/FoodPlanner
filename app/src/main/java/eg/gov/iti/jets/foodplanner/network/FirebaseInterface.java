package eg.gov.iti.jets.foodplanner.network;

import java.util.List;

import eg.gov.iti.jets.foodplanner.model.Meal;
import eg.gov.iti.jets.foodplanner.model.PlanMeal;

public interface FirebaseInterface {

    public void getMealsFromFirebase(List<Meal> mealList);
    public void getPlanMealsFromFirebase(List<PlanMeal> planMealList);

}
