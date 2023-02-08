package eg.gov.iti.jets.foodplanner.network;

import eg.gov.iti.jets.foodplanner.model.CategoryRoot;
import eg.gov.iti.jets.foodplanner.model.CountryRoot;
import eg.gov.iti.jets.foodplanner.model.Meal;
import eg.gov.iti.jets.foodplanner.model.MealRoot;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("random.php")
    Call<MealRoot> getRandomMeal();

    @GET("filter.php?a=Egyptian")
    Call<MealRoot> getEgyptianMeals();

    @GET("lookup.php?i=52772")
    Call<MealRoot> getMealById();


    @GET("list.php?c=list")
    Call<CategoryRoot> getAllCategories();

    @GET("list.php?a=list")
    Call<CountryRoot> getAllCountries();

    @GET("filter.php")
    Call<MealRoot> getSpecificCategoryMeals(@Query("c") String selectedCategory);

    @GET("filter.php")
    Call<MealRoot> getSpecificCountryMeals(@Query("a") String selectedCountry);



//    @GET("categories.php")
//    Call<CategoryRoot> getAllCategories();



}
