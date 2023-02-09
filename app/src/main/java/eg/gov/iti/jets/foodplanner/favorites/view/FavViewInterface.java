package eg.gov.iti.jets.foodplanner.favorites.view;


import java.util.List;

import eg.gov.iti.jets.foodplanner.model.Meal;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public interface FavViewInterface {

    public void getStoredFavMeals(List<Meal> mealsList);

    public void removeMealFromFav(Meal meal);

}
