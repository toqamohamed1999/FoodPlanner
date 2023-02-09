package eg.gov.iti.jets.foodplanner.homeScreen.presenter;

import eg.gov.iti.jets.foodplanner.model.Meal;

public interface HomePresenterInterface {
    public void getRandomMeal();

    public void getEgyptianMeals();

    public void getMealById();

    public void insertFavMeal(Meal meal);
}
