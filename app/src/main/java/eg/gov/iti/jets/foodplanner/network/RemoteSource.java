package eg.gov.iti.jets.foodplanner.network;

public interface RemoteSource {
     void enqueueRandomMealCall(NetworkDelegate networkDelegate);

     void enqueueEgyptianMealsCall(NetworkDelegate networkDelegate);

     void enqueueGetMealByIdCall(NetworkDelegate networkDelegate);

}
