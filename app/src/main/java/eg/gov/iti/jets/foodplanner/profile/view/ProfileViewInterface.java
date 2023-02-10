package eg.gov.iti.jets.foodplanner.profile.view;

import java.util.List;

import eg.gov.iti.jets.foodplanner.model.Meal;

public interface ProfileViewInterface {

    public void onResultGetMealsFromFirebase(String message) ;

    public void onResultStoreMealsToFirebase(String message);

}
