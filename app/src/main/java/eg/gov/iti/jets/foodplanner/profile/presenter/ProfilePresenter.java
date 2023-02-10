package eg.gov.iti.jets.foodplanner.profile.presenter;

import android.util.Log;
import android.widget.Toast;

import java.util.List;

import eg.gov.iti.jets.foodplanner.model.Meal;
import eg.gov.iti.jets.foodplanner.model.RepositoryInterface;
import eg.gov.iti.jets.foodplanner.network.NetworkProfileDelegate;
import eg.gov.iti.jets.foodplanner.profile.view.ProfileViewInterface;

public class ProfilePresenter implements ProfilePresenterInterface, NetworkProfileDelegate {

    private static final String TAG = "ProfilePresenter";
    ProfileViewInterface profileViewInterface;

    RepositoryInterface repositoryInterface;

    public ProfilePresenter(ProfileViewInterface profileViewInterface, RepositoryInterface repositoryInterface) {
        this.profileViewInterface = profileViewInterface;
        this.repositoryInterface = repositoryInterface;
    }


    @Override
    public void onResultGetMealsFromFirebase(String message) {
        profileViewInterface.onResultGetMealsFromFirebase(message);
    }
    @Override

    public void onResultStoreMealsToFirebase(String message) {
        profileViewInterface.onResultStoreMealsToFirebase(message);
    }
    @Override
    public void getMealsFromFirebase() {
        repositoryInterface.getMealsFromFirebase(this);
    }

    @Override
    public void storeMealsToFirebase() {
        repositoryInterface.storeMealsToFirebase(this);
    }
}
