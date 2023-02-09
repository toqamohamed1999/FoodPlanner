package eg.gov.iti.jets.foodplanner.database;

import android.content.Context;
import android.util.Log;

import java.util.List;

import eg.gov.iti.jets.foodplanner.model.Meal;
import eg.gov.iti.jets.foodplanner.model.PlanMeal;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class LocalSource implements LocalSourceInterface {
    private static final String TAG = "LocalSource";
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
    public Flowable<List<Meal>> getStoredMeals() {
        return mealDao.getStoredMeals();
    }

    @Override
    public void insertMeal(Meal meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDao.insertMeal(meal);
            }
        }).start();
    }

    @Override
    public void deleteMeal(Meal meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDao.deleteMeal(meal);
            }
        }).start();
    }

    @Override
    public void deleteMealTable() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDao.deleteMealTable();
            }
        }).start();
    }

    @Override
    public Flowable<List<PlanMeal>> getStoredPlanMeals(String day) {
        return planMealDao.getStoredPlanMeals(day);
    }

    @Override
    public void insertPlanMeal(PlanMeal planMeal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                planMealDao.insertPlanMeal(planMeal);
            }
        }).start();
    }

    @Override
    public void deletePlanMeal(PlanMeal planMeal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                planMealDao.deletePlanMeal(planMeal);
            }
        }).start();
    }

    @Override
    public void deletePlanMealTable() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                planMealDao.deletePlanMealTable();
            }
        }).start();
    }

    @Override
    public boolean MealIsExistInFav(String idMeal) {
        final boolean[] flag = new boolean[1];
        new Thread(new Runnable() {
            @Override
            public void run() {
                 flag[0] =mealDao.isRowIsExist(idMeal);
            }
        }).start();
        Log.i(TAG, "MealIsExistInFav: "+flag[0]);
        return flag[0];
    }

}
