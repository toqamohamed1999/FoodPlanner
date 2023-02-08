package eg.gov.iti.jets.foodplanner.network;

import java.util.List;

import eg.gov.iti.jets.foodplanner.model.Meal;
import eg.gov.iti.jets.foodplanner.model.MealRoot;
import io.reactivex.rxjava3.core.Single;

public interface NetworkDelegateSearchResult {


    public void getSpecificCategoryMeals(Single<MealRoot> mealRoot);

    public void getSpecificCountryMeals(Single<MealRoot> mealRoot);
    public void getMealByName(Single<MealRoot> mealRoot);
}
