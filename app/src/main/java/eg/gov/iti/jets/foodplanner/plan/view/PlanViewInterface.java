package eg.gov.iti.jets.foodplanner.plan.view;

import java.util.List;

import eg.gov.iti.jets.foodplanner.model.Meal;
import eg.gov.iti.jets.foodplanner.model.PlanMeal;

public interface PlanViewInterface {
    void getStoredPlanMeals(List<PlanMeal> mealList);
}
