package eg.gov.iti.jets.foodplanner.model;

import android.content.Context;

import java.util.List;

import eg.gov.iti.jets.foodplanner.database.LocalSourceInterface;
import eg.gov.iti.jets.foodplanner.network.NetworkDelegate;
import eg.gov.iti.jets.foodplanner.network.NetworkDelegateOnSearching;
import eg.gov.iti.jets.foodplanner.network.NetworkDelegateSearch;
import eg.gov.iti.jets.foodplanner.network.NetworkDelegateSearchResult;
import eg.gov.iti.jets.foodplanner.network.RemoteSourceInterface;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;


public class Repo implements RepositoryInterface {

    private Context context;
    private static Repo repo;
    private LocalSourceInterface localSource;
    private RemoteSourceInterface remoteSource;

    public Repo(Context context, LocalSourceInterface localSource, RemoteSourceInterface remoteSource) {
        this.context = context;
        this.localSource = localSource;
        this.remoteSource = remoteSource;
    }

    public static Repo getInstance(Context context, LocalSourceInterface localSource, RemoteSourceInterface remoteSource) {
        if (repo == null) {
            repo = new Repo(context, localSource, remoteSource);
        }
        return repo;
    }

    @Override
    public void getRandomMeal(NetworkDelegate networkDelegate) {
        remoteSource.enqueueRandomMealCall(networkDelegate);
    }

    @Override
    public void getEgyptianMeals(NetworkDelegate networkDelegate) {
        remoteSource.enqueueEgyptianMealsCall(networkDelegate);
    }

    @Override
    public void getMealById(NetworkDelegate networkDelegate) {
        remoteSource.enqueueGetMealByIdCall(networkDelegate);
    }

    @Override
    public void getAllCategories(NetworkDelegateSearch networkDelegateSearch) {
        remoteSource.enqueueGetAllCategoriesCall(networkDelegateSearch);
    }

    @Override
    public void getAllCountries(NetworkDelegateSearch networkDelegateSearch) {
        remoteSource.enqueueGetAllCountriesCall(networkDelegateSearch);
    }

    @Override
    public void getSpecificCategoryMeals(NetworkDelegateSearchResult networkDelegateSearchResult, String category) {
        remoteSource.enqueueGetSpecificCategoryMealsCall(networkDelegateSearchResult, category);
    }

    @Override
    public void getSpecificCountryMeals(NetworkDelegateSearchResult networkDelegateSearchResult, String country) {
        remoteSource.enqueueGetSpecificCountryMealsCall(networkDelegateSearchResult, country);
    }

    @Override
    public void getMealByName(NetworkDelegateSearchResult networkDelegateSearchResult, String Name) {
        remoteSource.enqueueGetMealByNameCall(networkDelegateSearchResult, Name);

    }

    @Override
    public void getMealsByCountry(NetworkDelegateOnSearching networkDelegateOnSearching, String country) {
        remoteSource.enqueueGetMealsByCountryCall(networkDelegateOnSearching, country);
    }

    @Override
    public void getMealsByIngredients(NetworkDelegateOnSearching networkDelegateOnSearching, String ingredient) {
        remoteSource.enqueueGetMealsByIngredientsCall(networkDelegateOnSearching, ingredient);
    }

    @Override
    public void getMealsByCategory(NetworkDelegateOnSearching networkDelegateOnSearching, String category) {
        remoteSource.enqueueGetMealsByCategoryCall(networkDelegateOnSearching, category);
    }

    @Override
    public void getMealsByFirstLetter(NetworkDelegateOnSearching networkDelegateOnSearching, String firstLetter) {
        remoteSource.enqueueGetMealsByFirstLetter(networkDelegateOnSearching, firstLetter);
    }

    @Override
    public void getMealByName(NetworkDelegateOnSearching networkDelegateOnSearching, String name) {
        remoteSource.enqueueGetMealByNameCall(networkDelegateOnSearching, name);
    }

    @Override
    public Flowable<List<Meal>> getStoreFavMeals() {
        return localSource.getStoredMeals();
    }

    @Override
    public void insertFavMeal(Meal meal) {
         localSource.insertMeal(meal);
    }

    @Override
    public void deleteFavMeal(Meal meal) {
        localSource.deleteMeal(meal);
    }


}
