package eg.gov.iti.jets.foodplanner.plan.presenter;

import eg.gov.iti.jets.foodplanner.MealDetails.view.MealDetailsViewInterface;
import eg.gov.iti.jets.foodplanner.model.Meal;
import eg.gov.iti.jets.foodplanner.model.PlanMeal;
import eg.gov.iti.jets.foodplanner.model.RepositoryInterface;
import eg.gov.iti.jets.foodplanner.plan.view.PlanViewInterface;

public class PlanPresenter implements  PlanPresenterInterface{

    private RepositoryInterface repositoryInterface;
    PlanViewInterface planViewInterface;

    public PlanPresenter(PlanViewInterface planViewInterface, RepositoryInterface repositoryInterface) {
        this.planViewInterface = planViewInterface;
        this.repositoryInterface = repositoryInterface;
    }
    @Override
    public void insertPlanMeal(PlanMeal meal) {
        repositoryInterface.insertPlanMeal(meal);
    }

    @Override
    public void deletePlanMeal(PlanMeal meal) {
        repositoryInterface.deletePlanMeal(meal);
    }


}
