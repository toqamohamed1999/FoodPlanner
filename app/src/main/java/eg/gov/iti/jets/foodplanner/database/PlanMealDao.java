package eg.gov.iti.jets.foodplanner.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
import eg.gov.iti.jets.foodplanner.model.PlanMeal;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface PlanMealDao {

    @Query("SELECT * FROM planMeal where weekDay=:day")
    Flowable<List<PlanMeal>> getStoredPlanMeals(String  day);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPlanMeal(PlanMeal planMeal);

    @Delete
    void deletePlanMeal(PlanMeal planMeal);

    @Query("DELETE FROM planMeal")
    void deletePlanMealTable();
}
