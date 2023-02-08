package eg.gov.iti.jets.foodplanner.network;

import eg.gov.iti.jets.foodplanner.model.CategoryRoot;
import eg.gov.iti.jets.foodplanner.model.CountryRoot;
import eg.gov.iti.jets.foodplanner.model.MealRoot;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("random.php")
    Single<MealRoot> getRandomMeal();

    @GET("filter.php?a=Egyptian")
    Single<MealRoot> getEgyptianMeals();

    @GET("lookup.php?i=52772")
    Single<MealRoot> getMealById();

    @GET("search.php") //aw s
    Single<MealRoot> getMealByName(@Query("f") String name);


    @GET("list.php?c=list")
    Single<CategoryRoot> getAllCategories();

    @GET("list.php?a=list")
    Single<CountryRoot> getAllCountries();

    @GET("filter.php")
    Single<MealRoot> getSpecificCategoryMeals(@Query("c") String selectedCategory);

    @GET("filter.php")
    Single<MealRoot> getSpecificCountryMeals(@Query("a") String selectedCountry);


}
