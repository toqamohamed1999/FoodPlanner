package eg.gov.iti.jets.foodplanner.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import eg.gov.iti.jets.foodplanner.model.Meal;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface MealDao {

    @Query("SELECT * FROM Meal")
    Observable<List<Meal>> getStoredMeals();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMeal(Meal meal);

    @Delete
    void deleteMeal(Meal meal);

    @Query("DELETE FROM Meal")
    void deleteMealTable();

//    @Query("select * from product")
//    Single<List<Product>> getAllProducts();
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void insertProduct(Product product);
//
//    @Delete
//    void deleteProduct(Product product);
}
