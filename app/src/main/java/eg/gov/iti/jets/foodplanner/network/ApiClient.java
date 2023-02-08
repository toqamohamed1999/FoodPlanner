package eg.gov.iti.jets.foodplanner.network;

import android.content.Context;
import android.util.Log;

import java.util.List;

import eg.gov.iti.jets.foodplanner.model.CategoryRoot;
import eg.gov.iti.jets.foodplanner.model.CountryRoot;
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
/////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void startConnectGetAllCategories(NetworkDelegateSearch networkDelegateSearch) {

        Call<CategoryRoot> call = apiInterface.getAllCategories();

        Callback<CategoryRoot> callback = getAllCategoriesCallBack(networkDelegateSearch);

        call.enqueue(callback);
    }

    private Callback<CategoryRoot> getAllCategoriesCallBack(NetworkDelegateSearch networkDelegateSearch) {
        Callback<CategoryRoot> callback = new Callback<CategoryRoot>() {
            @Override
            public void onResponse(Call<CategoryRoot> call, Response<CategoryRoot> response) {
                if (response != null && response.isSuccessful()) {
                    networkDelegateSearch.onSuccessGetAllCategories(response.body().getCategories());
                }
            }

            @Override
            public void onFailure(Call<CategoryRoot> call, Throwable t) {
                networkDelegateSearch.onFailGetAllCategories(t.getMessage());
                t.printStackTrace();
            }
        };
        return callback;
    }

    public void startConnectGetAllCountries(NetworkDelegateSearch networkDelegateSearch) {

        Call<CountryRoot> call = apiInterface.getAllCountries();

        Callback<CountryRoot> callback = getAllCountriesCallBack(networkDelegateSearch);

        call.enqueue(callback);
    }

    private Callback<CountryRoot> getAllCountriesCallBack(NetworkDelegateSearch networkDelegateSearch) {
        Callback<CountryRoot> callback = new Callback<CountryRoot>() {
            @Override
            public void onResponse(Call<CountryRoot> call, Response<CountryRoot> response) {
                if (response != null && response.isSuccessful()) {
                    networkDelegateSearch.onSuccessGetAllCountries(response.body().getCountries());
                }
            }

            @Override
            public void onFailure(Call<CountryRoot> call, Throwable t) {
                networkDelegateSearch.onFailGetAllCountries(t.getMessage());
                t.printStackTrace();
            }
        };
        return callback;
    }

    /////////////////////////////////////////////////////////
    public void startConnectGetSpecificCategoryMeals(NetworkDelegateSearchResult networkDelegateSearchResult,String category) {

        Call<MealRoot> call = apiInterface.getSpecificCategoryMeals(category);

        Callback<MealRoot> callback = getspecificCategoryCallBack(networkDelegateSearchResult);

        call.enqueue(callback);
    }

    private Callback<MealRoot> getspecificCategoryCallBack(NetworkDelegateSearchResult networkDelegateSearchResult) {
        Callback<MealRoot> callback = new Callback<MealRoot>() {
            @Override
            public void onResponse(Call<MealRoot> call, Response<MealRoot> response) {
                if (response != null && response.isSuccessful()) {
                    Log.i(TAG, "onResponse: getspecificCategoryCallBack "+response.body().getMeals());
                    networkDelegateSearchResult.onSuccessGetSpecificCategoryMeals(response.body().getMeals());
                }
            }

            @Override
            public void onFailure(Call<MealRoot> call, Throwable t) {
                networkDelegateSearchResult.onFailGetSpecificCategoryMeals(t.getMessage());
                Log.i(TAG, "onFailure: "+t.getMessage());
                t.printStackTrace();
            }
        };
        return callback;
    }

    public void startConnectGetSpecificCountryMeals(NetworkDelegateSearchResult networkDelegateSearchResult,String country) {

        Call<MealRoot> call = apiInterface.getSpecificCountryMeals(country);

        Callback<MealRoot> callback = getspecificCountryCallBack(networkDelegateSearchResult);

        call.enqueue(callback);
    }

    private Callback<MealRoot> getspecificCountryCallBack(NetworkDelegateSearchResult networkDelegateSearchResult) {
        Callback<MealRoot> callback = new Callback<MealRoot>() {
            @Override
            public void onResponse(Call<MealRoot> call, Response<MealRoot> response) {
                if (response != null && response.isSuccessful()) {
                    Log.i(TAG, "onResponse: getspecificCountryCallBack "+response.body().getMeals());
                    networkDelegateSearchResult.onSuccessGetSpecificCategoryMeals(response.body().getMeals());
                }
            }

            @Override
            public void onFailure(Call<MealRoot> call, Throwable t) {
                networkDelegateSearchResult.onFailGetSpecificCategoryMeals(t.getMessage());
                Log.i(TAG, "onFailure: "+t.getMessage());
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

    @Override
    public void enqueueGetAllCategoriesCall(NetworkDelegateSearch networkDelegateSearch) {
        startConnectGetAllCategories(networkDelegateSearch);
    }

    @Override
    public void enqueueGetAllCountriesCall(NetworkDelegateSearch networkDelegateSearch) {
         startConnectGetAllCountries(networkDelegateSearch);
    }


    @Override
    public void enqueueGetSpecificCountryMealsCall(NetworkDelegateSearchResult networkDelegateSearchResult,String country) {
          startConnectGetSpecificCountryMeals(networkDelegateSearchResult,country);
    }

    @Override
    public void enqueueGetSpecificCategoryMealsCall(NetworkDelegateSearchResult networkDelegateSearchResult,String category) {
          startConnectGetSpecificCategoryMeals(networkDelegateSearchResult,category);
    }
}
