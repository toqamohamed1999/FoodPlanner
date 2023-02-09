package eg.gov.iti.jets.foodplanner.favorites.presenter;


import eg.gov.iti.jets.foodplanner.model.Meal;

public interface FavPresenterInterface {

    public void getStoredFavMeals();

    public void removeMealFromFav(Meal meal);
}
