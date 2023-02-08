package eg.gov.iti.jets.foodplanner.network;

public interface RemoteSourceInterface {
     void enqueueRandomMealCall(NetworkDelegate networkDelegate);

     void enqueueEgyptianMealsCall(NetworkDelegate networkDelegate);

     void enqueueGetMealByIdCall(NetworkDelegate networkDelegate);
     void enqueueGetMealByNameCall(NetworkDelegateSearchResult networkDelegate,String name);

     void enqueueGetAllCategoriesCall(NetworkDelegateSearch networkDelegateSearch);

     void enqueueGetAllCountriesCall(NetworkDelegateSearch networkDelegateSearch);

     void enqueueGetSpecificCategoryMealsCall(NetworkDelegateSearchResult networkDelegateSearchResult,String category);

     void enqueueGetSpecificCountryMealsCall(NetworkDelegateSearchResult networkDelegateSearchResult,String country);

}
