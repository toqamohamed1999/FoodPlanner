package eg.gov.iti.jets.foodplanner.model;

import android.content.Context;

import eg.gov.iti.jets.foodplanner.database.LocalSource;
import eg.gov.iti.jets.foodplanner.network.NetworkDelegate;
import eg.gov.iti.jets.foodplanner.network.RemoteSource;


public class Repo implements RepositoryInterface {

    private Context context;
    private static Repo repo;
    private LocalSource localSource;
    private RemoteSource remoteSource;

    public Repo(Context context, LocalSource localSource, RemoteSource remoteSource) {
        this.context = context;
        this.localSource = localSource;
        this.remoteSource = remoteSource;
    }

    public static Repo getInstance(Context context, LocalSource localSource, RemoteSource remoteSource) {
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
