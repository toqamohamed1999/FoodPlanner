package eg.gov.iti.jets.foodplanner.model;

import androidx.lifecycle.LiveData;

import java.util.List;

import eg.gov.iti.jets.foodplanner.network.NetworkDelegate;

public interface RepositoryInterface {

      public void getRandomMeal(NetworkDelegate networkDelegate);

      public void getEgyptianMeals(NetworkDelegate networkDelegate);

      public void getMealById(NetworkDelegate networkDelegate);

//    void getAllProducts(NetworkDelegate networkDelegate);
//
////    LiveData<List<Product>> getStoreProducts();
////
////    void insertProduct(Product product);
////
////    void deleteProduct(Product product);
}
