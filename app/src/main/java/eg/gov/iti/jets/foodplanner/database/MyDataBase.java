package eg.gov.iti.jets.foodplanner.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import eg.gov.iti.jets.foodplanner.model.Meal;
import eg.gov.iti.jets.foodplanner.model.PlanMeal;


@Database(entities = {Meal.class, PlanMeal.class},version = 1)
public abstract class MyDataBase extends RoomDatabase {
    private static  MyDataBase myDataBase = null;

    public  abstract MealDao mealDao();
    public  abstract PlanMealDao planMealDao();

    public static synchronized MyDataBase getInstance(Context context){
        if(myDataBase == null){

            myDataBase = Room.databaseBuilder(context,MyDataBase.class,"mydatabase")
                    .build();
        }
        return myDataBase;
    }
}
