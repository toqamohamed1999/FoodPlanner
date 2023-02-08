package eg.gov.iti.jets.foodplanner.model;

import androidx.lifecycle.LiveData;

import java.util.List;

import eg.gov.iti.jets.foodplanner.network.NetworkDelegate;
import eg.gov.iti.jets.foodplanner.network.NetworkDelegateSearch;
import eg.gov.iti.jets.foodplanner.network.NetworkDelegateSearchResult;

public interface RepositoryInterface {

      public void getRandomMeal(NetworkDelegate networkDelegate);

      public void getEgyptianMeals(NetworkDelegate networkDelegate);

      public void getMealById(NetworkDelegate networkDelegate);

      public void getAllCategories(NetworkDelegateSearch networkDelegateSearch);

      public void getAllCountries(NetworkDelegateSearch networkDelegateSearch) ;

      public void getSpecificCategoryMeals(NetworkDelegateSearchResult networkDelegateSearchResult,String category);

      public void getSpecificCountryMeals(NetworkDelegateSearchResult networkDelegateSearchResult,String country);
      public void getMealByName(NetworkDelegateSearchResult networkDelegateSearchResult,String Name);


//    void getAllProducts(NetworkDelegate networkDelegate);
//
////    LiveData<List<Product>> getStoreProducts();
////
////    void insertProduct(Product product);
////
////    void deleteProduct(Product product);
}
