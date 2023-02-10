package eg.gov.iti.jets.foodplanner.network;

import java.util.List;

import eg.gov.iti.jets.foodplanner.model.Meal;

public interface NetworkProfileDelegate {

    public void onResultGetMealsFromFirebase(String message) ;

    public void onResultStoreMealsToFirebase(String message);

}
