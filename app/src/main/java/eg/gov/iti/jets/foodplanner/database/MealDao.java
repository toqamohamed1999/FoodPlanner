package eg.gov.iti.jets.foodplanner.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
@Dao
public interface MealDao {

//    @Query("select * from product")
//    LiveData<List<Product>> getAllProducts();
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void insertProduct(Product product);
//
//    @Delete
//    void deleteProduct(Product product);
}
