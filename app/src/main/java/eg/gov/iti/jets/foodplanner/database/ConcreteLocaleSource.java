package eg.gov.iti.jets.foodplanner.database;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

import eg.gov.iti.jets.foodplanner.model.Meal;

public class ConcreteLocaleSource implements LocalSource {

    private MealDao productsDao;
    private static ConcreteLocaleSource concreteLocaleSource;

    private ConcreteLocaleSource(Context context) {
        MyDataBase myDataBase = MyDataBase.getInstance(context);
        productsDao = myDataBase.productsDao();
    }

    public static ConcreteLocaleSource getInstance(Context context) {
        if (concreteLocaleSource == null) {
            concreteLocaleSource = new ConcreteLocaleSource(context);
        }
        return concreteLocaleSource;
    }

    @Override
    public LiveData<List<Meal>> getAllMeals() {
        return null;
    }
}
