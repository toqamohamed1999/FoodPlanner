package eg.gov.iti.jets.foodplanner.favorites.presenter;

import static android.content.ContentValues.TAG;

import android.util.Log;

import java.util.List;

import eg.gov.iti.jets.foodplanner.favorites.view.FavViewInterface;
import eg.gov.iti.jets.foodplanner.model.Meal;
import eg.gov.iti.jets.foodplanner.model.RepositoryInterface;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavPresenter implements FavPresenterInterface{

    private FavViewInterface favViewInterface;

    private RepositoryInterface repositoryInterface;

    public FavPresenter(FavViewInterface favViewInterface, RepositoryInterface repositoryInterface) {
        this.favViewInterface = favViewInterface;
        this.repositoryInterface = repositoryInterface;
    }

    @Override
    public void getStoredFavMeals() {
        repositoryInterface.getStoreFavMeals().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(meals -> favViewInterface.getStoredFavMeals(meals),
                        error-> Log.i(TAG, "getStoredFavMeals: "+error));

    }

    @Override
    public void removeMealFromFav(Meal meal) {
        repositoryInterface.deleteFavMeal(meal);
    }
}
