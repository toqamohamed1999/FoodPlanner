package eg.gov.iti.jets.foodplanner.network;

import eg.gov.iti.jets.foodplanner.model.Meal;
import eg.gov.iti.jets.foodplanner.model.MealRoot;
import io.reactivex.rxjava3.core.Single;

public interface NetworkDelegateDetails {

    public void getMealDetailsById(Single<MealRoot> mealRoot);
}
