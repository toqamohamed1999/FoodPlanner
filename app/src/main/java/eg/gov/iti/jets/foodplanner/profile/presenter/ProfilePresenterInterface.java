package eg.gov.iti.jets.foodplanner.profile.presenter;

import java.util.List;

import eg.gov.iti.jets.foodplanner.model.Meal;

public interface ProfilePresenterInterface {
    //get backup
    //store Data Backup
    public void getMealsFromFirebase();

    public void storeMealsToFirebase();
}
