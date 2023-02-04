package eg.gov.iti.jets.foodplanner;

import java.util.ArrayList;

public class Country {
    private String strArea;

    public Country(String strArea) {
        this.strArea = strArea;
    }
    public Country(){

    }

    public String getStrArea() {
        return strArea;
    }

    public void setStrArea(String strArea) {
        this.strArea = strArea;
    }

    @Override
    public String toString() {
        return "Country{" +
                "strArea='" + strArea + '\'' +
                '}';
    }
}

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

