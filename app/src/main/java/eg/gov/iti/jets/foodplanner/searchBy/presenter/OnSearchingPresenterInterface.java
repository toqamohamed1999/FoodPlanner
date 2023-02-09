package eg.gov.iti.jets.foodplanner.searchBy.presenter;

public interface OnSearchingPresenterInterface {

    public void getMealsBYCountry(String country);

    public void getMealsByIngredients(String ingredient);
    public void getMealsByCategory(String category);
    public void getMealsByFirstLetter(String firstLetter);
    public void getMealByName(String name);

}
