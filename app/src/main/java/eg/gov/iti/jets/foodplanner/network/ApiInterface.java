package eg.gov.iti.jets.foodplanner.network;

import eg.gov.iti.jets.foodplanner.model.Meal;
import eg.gov.iti.jets.foodplanner.model.MealRoot;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("random.php")
    Call<MealRoot> getRandomMeal();

    @GET("filter.php?a=Egyptian")
    Call<MealRoot> getEgyptianMeals();

    @GET("lookup.php?i=52772")
    Call<MealRoot> getMealById();

}
