package eg.gov.iti.jets.foodplanner.network;

import android.content.Context;
import android.util.Log;

import java.util.List;

import eg.gov.iti.jets.foodplanner.model.Meal;
import eg.gov.iti.jets.foodplanner.model.MealRoot;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient implements RemoteSource {

    private static final String TAG = "ApiClient";

    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    private Retrofit retrofit = null;

    private Context context;

    private List<Meal> mealsList;

    private static ApiClient apiClient;

    ApiInterface apiInterface;


    private ApiClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
         apiInterface = retrofit.create(ApiInterface.class);
    }

    public static synchronized ApiClient getClient() {
        if (apiClient == null) {
            apiClient = new ApiClient();
        }
        return apiClient;
    }


    public void startConnectRandomMeal(NetworkDelegate networkDelegate) {

        Call<MealRoot> call = apiInterface.getRandomMeal();

        Callback<MealRoot> callback = makeCallBack(networkDelegate);

        call.enqueue(callback);
    }

    private Callback<MealRoot> makeCallBack(NetworkDelegate networkDelegate) {
        Callback<MealRoot> callback = new Callback<MealRoot>() {
            @Override
            public void onResponse(Call<MealRoot> call, Response<MealRoot> response) {
                if (response != null && response.isSuccessful()) {
                    networkDelegate.onSuccessGetRandomMeal(response.body().getMeals().get(0));
                }
            }

            @Override
            public void onFailure(Call<MealRoot> call, Throwable t) {
                networkDelegate.onFailGetRandomMeal(t.getMessage());
                t.printStackTrace();
            }
        };
        return callback;
    }
    public void startConnectEgyptianMeals(NetworkDelegate networkDelegate) {

        Call<MealRoot> call = apiInterface.getEgyptianMeals();

        Callback<MealRoot> callback = egyptianMealsCallBack(networkDelegate);

        call.enqueue(callback);
    }

    private Callback<MealRoot> egyptianMealsCallBack(NetworkDelegate networkDelegate) {
        Callback<MealRoot> callback = new Callback<MealRoot>() {
            @Override
            public void onResponse(Call<MealRoot> call, Response<MealRoot> response) {
                if (response != null && response.isSuccessful()) {
                    networkDelegate.onSuccessGetEgyptianMeals(response.body().getMeals());
                }
            }

            @Override
            public void onFailure(Call<MealRoot> call, Throwable t) {
                networkDelegate.onFailGetEgyptianMeals(t.getMessage());
                t.printStackTrace();
            }
        };
        return callback;
    }

    public void startConnectGetMealById(NetworkDelegate networkDelegate) {

        Call<MealRoot> call = apiInterface.getMealById();

        Callback<MealRoot> callback = getMealByIDCallBack(networkDelegate);

        call.enqueue(callback);
    }

    private Callback<MealRoot> getMealByIDCallBack(NetworkDelegate networkDelegate) {
        Callback<MealRoot> callback = new Callback<MealRoot>() {
            @Override
            public void onResponse(Call<MealRoot> call, Response<MealRoot> response) {
                if (response != null && response.isSuccessful()) {
                    networkDelegate.onSuccessGetMealByName(response.body().getMeals().get(0));
                }
            }

            @Override
            public void onFailure(Call<MealRoot> call, Throwable t) {
                networkDelegate.onFailGetMealByName(t.getMessage());
                t.printStackTrace();
            }
        };
        return callback;
    }

    @Override
    public void enqueueRandomMealCall(NetworkDelegate networkDelegate) {
        startConnectRandomMeal(networkDelegate);
    }

    @Override
    public void enqueueEgyptianMealsCall(NetworkDelegate networkDelegate) {
        startConnectEgyptianMeals(networkDelegate);
    }

    @Override
    public void enqueueGetMealByIdCall(NetworkDelegate networkDelegate) {
        startConnectGetMealById(networkDelegate);
    }
}
