package eg.gov.iti.jets.foodplanner.model;

import android.content.Context;
import android.util.Log;

import java.util.List;

import eg.gov.iti.jets.foodplanner.MealDetails.view.CheckFavInterface;
import eg.gov.iti.jets.foodplanner.MealDetails.view.MealDetailsViewInterface;
import eg.gov.iti.jets.foodplanner.database.LocalSourceInterface;
import eg.gov.iti.jets.foodplanner.network.FirebaseInterface;
import eg.gov.iti.jets.foodplanner.network.MyFireBase.MyFirebase;
import eg.gov.iti.jets.foodplanner.network.NetworkDelegate;
import eg.gov.iti.jets.foodplanner.network.NetworkDelegateDetails;
import eg.gov.iti.jets.foodplanner.network.NetworkDelegateOnSearching;
import eg.gov.iti.jets.foodplanner.network.NetworkDelegateSearch;
import eg.gov.iti.jets.foodplanner.network.NetworkDelegateSearchResult;
import eg.gov.iti.jets.foodplanner.network.NetworkProfileDelegate;
import eg.gov.iti.jets.foodplanner.network.RemoteSourceInterface;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class Repo implements RepositoryInterface, FirebaseInterface {

    private static final String TAG = "Repo";
    private Context context;
    private static Repo repo;
    private LocalSourceInterface localSource;
    private RemoteSourceInterface remoteSource;

    private MyFirebase myFirebase;

    public Repo(Context context, LocalSourceInterface localSource, RemoteSourceInterface remoteSource) {
        this.context = context;
        this.localSource = localSource;
        this.remoteSource = remoteSource;

        myFirebase = new MyFirebase(this);
    }

    public static Repo getInstance(Context context, LocalSourceInterface localSource, RemoteSourceInterface remoteSource) {
        if (repo == null) {
            repo = new Repo(context, localSource, remoteSource);
        }
        return repo;
    }

    @Override
    public void getRandomMeal(NetworkDelegate networkDelegate) {
        remoteSource.enqueueRandomMealCall(networkDelegate);
    }

    @Override
    public void getEgyptianMeals(NetworkDelegate networkDelegate) {
        remoteSource.enqueueEgyptianMealsCall(networkDelegate);
    }


    @Override
    public void getMealById(NetworkDelegateDetails networkDelegateDetails, String idMeal) {
        remoteSource.enqueueGetMealByIdCall(networkDelegateDetails,idMeal);
    }

    @Override
    public void getAllCategories(NetworkDelegateSearch networkDelegateSearch) {
        remoteSource.enqueueGetAllCategoriesCall(networkDelegateSearch);
    }

    @Override
    public void getAllCountries(NetworkDelegateSearch networkDelegateSearch) {
        remoteSource.enqueueGetAllCountriesCall(networkDelegateSearch);
    }

    @Override
    public void getSpecificCategoryMeals(NetworkDelegateSearchResult networkDelegateSearchResult, String category) {
        remoteSource.enqueueGetSpecificCategoryMealsCall(networkDelegateSearchResult, category);
    }

    @Override
    public void getSpecificCountryMeals(NetworkDelegateSearchResult networkDelegateSearchResult, String country) {
        remoteSource.enqueueGetSpecificCountryMealsCall(networkDelegateSearchResult, country);
    }

    @Override
    public void getMealByName(NetworkDelegateSearchResult networkDelegateSearchResult, String Name) {
        remoteSource.enqueueGetMealByNameCall(networkDelegateSearchResult, Name);

    }

    @Override
    public void getMealsByCountry(NetworkDelegateOnSearching networkDelegateOnSearching, String country) {
        remoteSource.enqueueGetMealsByCountryCall(networkDelegateOnSearching, country);
    }

    @Override
    public void getMealsByIngredients(NetworkDelegateOnSearching networkDelegateOnSearching, String ingredient) {
        remoteSource.enqueueGetMealsByIngredientsCall(networkDelegateOnSearching, ingredient);
    }

    @Override
    public void getMealsByCategory(NetworkDelegateOnSearching networkDelegateOnSearching, String category) {
        remoteSource.enqueueGetMealsByCategoryCall(networkDelegateOnSearching, category);
    }

    @Override
    public void getMealsByFirstLetter(NetworkDelegateOnSearching networkDelegateOnSearching, String firstLetter) {
        remoteSource.enqueueGetMealsByFirstLetter(networkDelegateOnSearching, firstLetter);
    }

    @Override
    public void getMealByName(NetworkDelegateOnSearching networkDelegateOnSearching, String name) {
        remoteSource.enqueueGetMealByNameCall(networkDelegateOnSearching, name);
    }

    @Override
    public Flowable<List<Meal>> getStoreFavMeals() {
        return localSource.getStoredMeals();
    }

    @Override
    public void insertFavMeal(Meal meal) {
         localSource.insertMeal(meal);
    }

    @Override
    public void deleteFavMeal(Meal meal) {
        localSource.deleteMeal(meal);
    }

    @Override
    public void MealIsExistInFav(CheckFavInterface checkFavInterface,String idMeal) {
        localSource.MealIsExistInFav(checkFavInterface,idMeal);
    }

    @Override
    public Flowable<List<PlanMeal>> getStoredPlanMeals(String day) {
        return localSource.getStoredPlanMeals(day);
    }

    @Override
    public void insertPlanMeal(PlanMeal meal) {
        localSource.insertPlanMeal(meal);
    }

    @Override
    public void deletePlanMeal(PlanMeal meal) {
        localSource.deletePlanMeal(meal);
    }


    public void getMealsFromFirebase(NetworkProfileDelegate networkProfileDelegate){
        myFirebase.getMealsFromFirebase(networkProfileDelegate);

    }

    public void storeMealsToFirebase(NetworkProfileDelegate networkProfileDelegate){
        localSource.getStoredMeals().subscribeOn(Schedulers.io())
                .filter(meals -> meals!=null && meals.size() > 0)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(meals -> myFirebase.storeMealsToFirebase(networkProfileDelegate,meals),
                        error -> Log.i(TAG, "getRandomMeal: "+error));

        localSource.getStoredPlanMeals().subscribeOn(Schedulers.io())
                .filter(planmeals -> planmeals!=null && planmeals.size() > 0)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(planmeals -> myFirebase.storePlanMealsToFirebase(networkProfileDelegate,planmeals),
                        error -> Log.i(TAG, "getRandomMeal: "+error));

    }

    @Override
    public void getPlanMealsFromFirebase(NetworkProfileDelegate networkProfileDelegate) {
        myFirebase.getPlanMealsFromFirebase(networkProfileDelegate);
    }

    @Override
    public void getMealsFromFirebase(List<Meal> mealList) {
        for(int i = 0;i< mealList.size(); i++) {
            localSource.insertMeal(mealList.get(i));
        }
    }

    @Override
    public void getPlanMealsFromFirebase(List<PlanMeal> planMealList) {
        for(int i = 0;i< planMealList.size(); i++) {
            localSource.insertPlanMeal(planMealList.get(i));
        }
    }
}
