package eg.gov.iti.jets.foodplanner.network;

import java.util.List;

import eg.gov.iti.jets.foodplanner.model.Meal;

public interface NetworkDelegateSearchResult {


    public void onSuccessGetSpecificCategoryMeals(List<Meal> mealsList);
    public void onFailGetSpecificCategoryMeals(String error);

    public void onSuccessGetSpecificCountryMeals(List<Meal> mealsList);
    public void onFailGetSpecificCountryMeals(String error);
}
