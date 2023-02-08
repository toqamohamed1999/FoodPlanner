package eg.gov.iti.jets.foodplanner.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
import eg.gov.iti.jets.foodplanner.model.PlanMeal;
import io.reactivex.rxjava3.core.Observable;

@Dao
public interface PlanMealDao {

    @Query("SELECT * FROM planMeal")
    Observable<List<PlanMeal>> getStoredPlanMeals();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPlanMeal(PlanMeal planMeal);

    @Delete
    void deletePlanMeal(PlanMeal planMeal);

    @Query("DELETE FROM planMeal")
    void deletePlanMealTable();
}
