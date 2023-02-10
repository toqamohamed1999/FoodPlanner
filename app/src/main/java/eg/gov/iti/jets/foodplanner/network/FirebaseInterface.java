package eg.gov.iti.jets.foodplanner.network;

import java.util.List;

import eg.gov.iti.jets.foodplanner.model.Meal;

public interface FirebaseInterface {

    public void getMealsFromFirebase(List<Meal> mealList);

}
