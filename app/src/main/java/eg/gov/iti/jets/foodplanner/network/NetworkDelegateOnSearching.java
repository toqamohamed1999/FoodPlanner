package eg.gov.iti.jets.foodplanner.network;

import eg.gov.iti.jets.foodplanner.model.MealRoot;
import io.reactivex.rxjava3.core.Single;

public interface NetworkDelegateOnSearching {

    public void getMealsByCountry(Single<MealRoot> mealRoot);

    public void getMealsByIngredients(Single<MealRoot> mealRoot);

}
