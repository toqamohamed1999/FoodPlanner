package eg.gov.iti.jets.foodplanner.MealDetails.presenter;

import eg.gov.iti.jets.foodplanner.MealDetails.view.MealDetailsViewInterface;
import eg.gov.iti.jets.foodplanner.homeScreen.view.HomeViewInterface;
import eg.gov.iti.jets.foodplanner.model.Meal;
import eg.gov.iti.jets.foodplanner.model.PlanMeal;
import eg.gov.iti.jets.foodplanner.model.RepositoryInterface;

public class MealDetailsPresenter implements MealDetailsPresenterInterface {
    private RepositoryInterface repositoryInterface;
    MealDetailsViewInterface mealDetailsViewInterface;

    public MealDetailsPresenter(MealDetailsViewInterface mealDetailsViewInterface, RepositoryInterface repositoryInterface) {
        this.mealDetailsViewInterface = mealDetailsViewInterface;
        this.repositoryInterface = repositoryInterface;
    }

    @Override
    public void insertFavMeal(Meal meal) {
        repositoryInterface.insertFavMeal(meal);
    }

    @Override
    public void deleteFavMeal(Meal meal) {
        repositoryInterface.deleteFavMeal(meal);
    }

    @Override
    public Boolean MealIsExistInFav(String idMeal) {
       return repositoryInterface.MealIsExistInFav(idMeal);
    }

    @Override
    public void insertPlanMeal(PlanMeal meal) {
        repositoryInterface.insertPlanMeal(meal);
    }
}
