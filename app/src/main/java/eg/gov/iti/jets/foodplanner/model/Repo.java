package eg.gov.iti.jets.foodplanner.model;

import android.content.Context;

import eg.gov.iti.jets.foodplanner.database.LocalSourceInterface;
import eg.gov.iti.jets.foodplanner.network.NetworkDelegate;
import eg.gov.iti.jets.foodplanner.network.NetworkDelegateSearch;
import eg.gov.iti.jets.foodplanner.network.NetworkDelegateSearchResult;
import eg.gov.iti.jets.foodplanner.network.RemoteSourceInterface;


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
    public void getSpecificCategoryMeals(NetworkDelegateSearchResult networkDelegateSearchResult,String category) {
        remoteSource.enqueueGetSpecificCategoryMealsCall(networkDelegateSearchResult, category);
    }

    @Override
    public void getSpecificCountryMeals(NetworkDelegateSearchResult networkDelegateSearchResult,String country) {
        remoteSource.enqueueGetSpecificCountryMealsCall(networkDelegateSearchResult,country);
    }







    //    @Override
//    public void getAllProducts(NetworkDelegate networkDelegate) {
//        remoteSource.enqueueCall(networkDelegate);
//    }
//
//    @Override
//    public LiveData<List<Product>> getStoreProducts() {
//        return localSource.getAllProducts();
//    }
//
//    @Override
//    public void insertProduct(Product product) {
//        localSource.insertProduct(product);
//    }
//
//    @Override
//    public void deleteProduct(Product product) {
//        localSource.deleteProduct(product);
//    }
}