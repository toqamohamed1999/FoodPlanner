package eg.gov.iti.jets.foodplanner.model;
import java.util.ArrayList;

import eg.gov.iti.jets.foodplanner.model.Country;

class CountryRoot {
    private ArrayList<Country> countries;

    public CountryRoot(ArrayList<Country> countries) {
        this.countries = countries;
    }
    public CountryRoot(){

    }
    public ArrayList<Country> getCountries() {
        return countries;
    }

    public void setCountries(ArrayList<Country> countries) {
        this.countries = countries;
    }

    @Override
    public String toString() {
        return "CountryRoot{" +
                "countries=" + countries +
                '}';
    }
}
