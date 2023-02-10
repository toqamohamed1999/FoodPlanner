package eg.gov.iti.jets.foodplanner.model;

import androidx.lifecycle.LiveData;

import java.util.List;

import eg.gov.iti.jets.foodplanner.network.NetworkDelegate;
import eg.gov.iti.jets.foodplanner.network.NetworkDelegateOnSearching;
import eg.gov.iti.jets.foodplanner.network.NetworkDelegateSearch;
import eg.gov.iti.jets.foodplanner.network.NetworkDelegateSearchResult;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public interface RepositoryInterface {

      public void getRandomMeal(NetworkDelegate networkDelegate);

      public void getEgyptianMeals(NetworkDelegate networkDelegate);

      public void getMealById(NetworkDelegate networkDelegate);

      public void getAllCategories(NetworkDelegateSearch networkDelegateSearch);

      public void getAllCountries(NetworkDelegateSearch networkDelegateSearch) ;

      public void getSpecificCategoryMeals(NetworkDelegateSearchResult networkDelegateSearchResult,String category);

      public void getSpecificCountryMeals(NetworkDelegateSearchResult networkDelegateSearchResult,String country);
      public void getMealByName(NetworkDelegateSearchResult networkDelegateSearchResult,String Name);

      public void getMealsByCountry(NetworkDelegateOnSearching networkDelegateOnSearching,String Country) ;

      public void getMealsByIngredients(NetworkDelegateOnSearching networkDelegateOnSearching,String ingredient);
      public void getMealsByCategory(NetworkDelegateOnSearching networkDelegateOnSearching,String category);
      public void getMealsByFirstLetter(NetworkDelegateOnSearching networkDelegateOnSearching,String firstLetter);
      public void getMealByName(NetworkDelegateOnSearching networkDelegateOnSearching,String name);

      Flowable<List<Meal>> getStoreFavMeals();

      void insertFavMeal(Meal meal);

      void deleteFavMeal(Meal meal);
      boolean MealIsExistInFav(String idMeal);
      Flowable<List<PlanMeal>> getStoredPlanMeals(String day);
      void insertPlanMeal(PlanMeal meal);
      void deletePlanMeal(PlanMeal meal);


}
