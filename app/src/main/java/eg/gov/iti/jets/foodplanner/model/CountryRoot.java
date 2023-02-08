package eg.gov.iti.jets.foodplanner.model;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import eg.gov.iti.jets.foodplanner.model.Country;

public class CountryRoot {

    @SerializedName("meals")
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
