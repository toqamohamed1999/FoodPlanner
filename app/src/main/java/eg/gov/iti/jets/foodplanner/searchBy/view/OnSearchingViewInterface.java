package eg.gov.iti.jets.foodplanner.searchBy.view;

import java.util.List;

import eg.gov.iti.jets.foodplanner.model.Meal;

public interface OnSearchingViewInterface {

    public void getMealsByCountry(List<Meal> mealsList);

    public void getMealsByIngredient(List<Meal> mealsList);
    public void getMealsByCategory(List<Meal> mealsList);
    public void getMealsByFirstletter(List<Meal> mealsList);
    public void getMealByName(List<Meal> mealsList);



}
