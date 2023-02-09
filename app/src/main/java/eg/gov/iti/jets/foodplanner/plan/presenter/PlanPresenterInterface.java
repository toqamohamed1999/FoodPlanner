package eg.gov.iti.jets.foodplanner.plan.presenter;

import eg.gov.iti.jets.foodplanner.model.Meal;
import eg.gov.iti.jets.foodplanner.model.PlanMeal;

public interface PlanPresenterInterface {
    public void insertPlanMeal(PlanMeal meal);
    public void deletePlanMeal(PlanMeal meal);
}
