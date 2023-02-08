package eg.gov.iti.jets.foodplanner.network;

import eg.gov.iti.jets.foodplanner.model.CategoryRoot;
import eg.gov.iti.jets.foodplanner.model.CountryRoot;
import eg.gov.iti.jets.foodplanner.model.MealRoot;
import io.reactivex.rxjava3.core.Single;

public class RemoteSource implements RemoteSourceInterface{

    private ApiInterface apiInterface;

    private static RemoteSource remoteSource;
    private RemoteSource() {
        ApiClient apiClient = ApiClient.getClient();
        apiInterface = apiClient.getApiInterface();
    }

    public static synchronized RemoteSource getRemoteSource() {
        if (remoteSource == null) {
            remoteSource = new RemoteSource();
        }
        return remoteSource;
    }


    @Override
    public void enqueueRandomMealCall(NetworkDelegate networkDelegate) {
            Single<MealRoot> observable = apiInterface.getRandomMeal();
            networkDelegate.getRandomMeal(observable);
    }

    @Override
    public void enqueueEgyptianMealsCall(NetworkDelegate networkDelegate) {
        Single<MealRoot> observable = apiInterface.getEgyptianMeals();
        networkDelegate.getEgyptianMeals(observable);
    }

    @Override
    public void enqueueGetMealByIdCall(NetworkDelegate networkDelegate) {
        Single<MealRoot> observable = apiInterface.getMealById();
        networkDelegate.getMealByName(observable);
    }

    @Override
    public void enqueueGetMealByNameCall(NetworkDelegateSearchResult networkDelegateSearchResult, String name) {
        Single<MealRoot> observable = apiInterface.getMealByName(name);
        networkDelegateSearchResult.getMealByName(observable);
    }


    @Override
    public void enqueueGetAllCategoriesCall(NetworkDelegateSearch networkDelegateSearch) {
        Single<CategoryRoot> observable = apiInterface.getAllCategories();
        networkDelegateSearch.getAllCategories(observable);
    }

    @Override
    public void enqueueGetAllCountriesCall(NetworkDelegateSearch networkDelegateSearch) {
        Single<CountryRoot> observable = apiInterface.getAllCountries();
        networkDelegateSearch.getAllCountries(observable);
    }

    @Override
    public void enqueueGetSpecificCategoryMealsCall(NetworkDelegateSearchResult networkDelegateSearchResult, String category) {
        Single<MealRoot> observable = apiInterface.getSpecificCategoryMeals(category);
        networkDelegateSearchResult.getSpecificCategoryMeals(observable);
    }

    @Override
    public void enqueueGetSpecificCountryMealsCall(NetworkDelegateSearchResult networkDelegateSearchResult, String country) {
        Single<MealRoot> observable = apiInterface.getSpecificCountryMeals(country);
        networkDelegateSearchResult.getSpecificCountryMeals(observable);
    }

    @Override
    public void enqueueGetMealsByCountryCall(NetworkDelegateOnSearching networkDelegateOnSearching,String country) {
        Single<MealRoot> observable = apiInterface.getMealsByCountry(country);
        networkDelegateOnSearching.getMealsByCountry(observable);
    }

    @Override
    public void enqueueGetMealsByIngredientsCall(NetworkDelegateOnSearching networkDelegateOnSearching,String ingredient) {
        Single<MealRoot> observable = apiInterface.getMealsByIngredients(ingredient);
        networkDelegateOnSearching.getMealsByIngredients(observable);
    }
}
