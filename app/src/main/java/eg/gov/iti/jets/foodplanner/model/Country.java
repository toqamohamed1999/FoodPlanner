package eg.gov.iti.jets.foodplanner.model;

public class Country {
    private String strArea;

    public Country(String strArea) {
        this.strArea = strArea;
    }

    public Country() {

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
