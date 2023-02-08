package eg.gov.iti.jets.foodplanner.network;

import java.util.List;

import eg.gov.iti.jets.foodplanner.model.Category;
import eg.gov.iti.jets.foodplanner.model.CategoryRoot;
import eg.gov.iti.jets.foodplanner.model.Country;
import eg.gov.iti.jets.foodplanner.model.CountryRoot;
import io.reactivex.rxjava3.core.Single;


public interface NetworkDelegateSearch {

    public void getAllCategories(Single<CategoryRoot> categoryRoot);

    public void getAllCountries(Single<CountryRoot> countryRoot);
}
