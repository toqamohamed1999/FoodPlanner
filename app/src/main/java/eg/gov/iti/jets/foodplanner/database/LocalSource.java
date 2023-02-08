package eg.gov.iti.jets.foodplanner.database;

import android.content.Context;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class LocalSource implements LocalSourceInterface{

    private MealDao mealDao;
    private static LocalSource localSource;

    private LocalSource(Context context) {
        MyDataBase myDataBase = MyDataBase.getInstance(context);
        mealDao = myDataBase.mealDao();
    }

    public static synchronized LocalSource getLocalSource(Context context) {
        if (localSource == null) {
            localSource = new LocalSource(context);
        }
        return localSource;
    }

//    public Single<List<Product>> getAllProducts() {
//        return productsDao.getAllProducts();
//    }
//
//    public void insertProduct(Product product) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                productsDao.insertProduct(product);
//            }
//        }).start();
//    }
//
//
//    public void deleteProduct(Product product) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                productsDao.deleteProduct(product);
//            }
//        }).start();
//    }
}
