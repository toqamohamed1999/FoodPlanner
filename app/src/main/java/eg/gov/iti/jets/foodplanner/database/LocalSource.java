package eg.gov.iti.jets.foodplanner.database;

import android.content.Context;

import java.util.List;

import eg.gov.iti.jets.foodplanner.model.Meal;
import eg.gov.iti.jets.foodplanner.model.PlanMeal;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class LocalSource implements LocalSourceInterface{

    private MealDao mealDao;

    private PlanMealDao planMealDao;
    private static LocalSource localSource;

    private LocalSource(Context context) {
        MyDataBase myDataBase = MyDataBase.getInstance(context);
        mealDao = myDataBase.mealDao();
        planMealDao = myDataBase.planMealDao();
    }

    public static synchronized LocalSource getLocalSource(Context context) {
        if (localSource == null) {
            localSource = new LocalSource(context);
        }
        return localSource;
    }

    @Override
    public Observable<List<Meal>> getStoredMeals() {
        return mealDao.getStoredMeals();
    }

    @Override
    public void insertMeal(Meal meal) {
        mealDao.insertMeal(meal);
    }

    @Override
    public void deleteMeal(Meal meal) {
        mealDao.deleteMeal(meal);
    }

    @Override
    public void deleteMealTable() {
          mealDao.deleteMealTable();
    }

    @Override
    public Observable<List<PlanMeal>> getStoredPlanMeals() {
        return planMealDao.getStoredPlanMeals();
    }

    @Override
    public void insertPlanMeal(PlanMeal planMeal) {
        planMealDao.insertPlanMeal(planMeal);
    }

    @Override
    public void deletePlanMeal(PlanMeal planMeal) {
          planMealDao.deletePlanMeal(planMeal);
    }

    @Override
    public void deletePlanMealTable() {
        planMealDao.deletePlanMealTable();
    }


//    public Single<List<Meal>> getAllProducts() {
//        return mealDao.getStoredMeals();
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
