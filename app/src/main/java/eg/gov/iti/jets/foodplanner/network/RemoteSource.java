package eg.gov.iti.jets.foodplanner.network;

public interface RemoteSource {
     void enqueueRandomMealCall(NetworkDelegate networkDelegate);

     void enqueueEgyptianMealsCall(NetworkDelegate networkDelegate);

     void enqueueGetMealByIdCall(NetworkDelegate networkDelegate);

     void enqueueGetAllCategoriesCall(NetworkDelegateSearch networkDelegateSearch);

     void enqueueGetAllCountriesCall(NetworkDelegateSearch networkDelegateSearch);

     void enqueueGetSpecificCategoryMealsCall(NetworkDelegateSearchResult networkDelegateSearchResult,String category);

     void enqueueGetSpecificCountryMealsCall(NetworkDelegateSearchResult networkDelegateSearchResult,String country);

}
