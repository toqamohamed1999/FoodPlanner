package eg.gov.iti.jets.foodplanner.network;

import android.util.Log;

import eg.gov.iti.jets.foodplanner.model.CategoryRoot;
import eg.gov.iti.jets.foodplanner.model.CountryRoot;
import eg.gov.iti.jets.foodplanner.model.MealRoot;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// ingredients images base url
// https://www.themealdb.com/images/ingredients/Lime.png
public class ApiClient {

    private static final String TAG = "ApiClient";

    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    private Retrofit retrofit = null;

    private static ApiClient apiClient;

    private ApiInterface apiInterface;


    private ApiClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
         apiInterface = retrofit.create(ApiInterface.class);
    }

    public static synchronized ApiClient getClient() {
        if (apiClient == null) {
            apiClient = new ApiClient();
        }
        return apiClient;
    }

    public ApiInterface getApiInterface() {
        return apiInterface;
    }

}
