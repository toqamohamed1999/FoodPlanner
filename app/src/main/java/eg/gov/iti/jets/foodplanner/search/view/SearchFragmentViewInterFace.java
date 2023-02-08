package eg.gov.iti.jets.foodplanner.search.view;

import java.util.List;

import eg.gov.iti.jets.foodplanner.model.Category;
import eg.gov.iti.jets.foodplanner.model.Country;
import eg.gov.iti.jets.foodplanner.model.Meal;

public interface SearchFragmentViewInterFace {

    public void getAllCategories(List<Category> categoriesList);

    public void getAllCountry(List<Country> countriesList);

}
