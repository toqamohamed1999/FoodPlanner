package eg.gov.iti.jets.foodplanner.network;

import java.util.List;

import eg.gov.iti.jets.foodplanner.model.Category;
import eg.gov.iti.jets.foodplanner.model.Country;


public interface NetworkDelegateSearch {

    public void onSuccessGetAllCategories(List<Category> categoriesList);
    public void onFailGetAllCategories(String error);

    public void onSuccessGetAllCountries(List<Country> countriesList);
    public void onFailGetAllCountries(String error);
}
