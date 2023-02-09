package eg.gov.iti.jets.foodplanner.plan.presenter;

import eg.gov.iti.jets.foodplanner.model.Meal;
import eg.gov.iti.jets.foodplanner.model.PlanMeal;

public interface PlanPresenterInterface {
    public void deletePlanMeal(PlanMeal meal);
    public void getStoredPlanMeals(String day);
}
