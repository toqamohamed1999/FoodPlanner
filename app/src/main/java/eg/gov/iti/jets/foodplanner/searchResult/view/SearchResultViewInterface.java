package eg.gov.iti.jets.foodplanner.searchResult.view;

import java.util.List;

import eg.gov.iti.jets.foodplanner.model.Meal;

public interface SearchResultViewInterface {

    public void getSpecificCategoryMeals(List<Meal> mealsList);

    public void getSpecificCountryMeals(List<Meal> mealsList);
}
