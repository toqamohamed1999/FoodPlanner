package eg.gov.iti.jets.foodplanner.model;



import java.util.ArrayList;

//private String idIngredient;
//private String strIngredient;
//private String strDescription;
public class Ingredient {

    private String idIngredient;
    private String strIngredient;
    private String strDescription;

    private String strThumbnail;
    public Ingredient() {
    }

    public Ingredient(String strIngredient, String strThumbnail) {
        this.strIngredient = strIngredient;
        this.strThumbnail = strThumbnail;
    }

    public Ingredient(String idIngredient, String strIngredient, String strDescription, String strThumbnail) {
        this.idIngredient = idIngredient;
        this.strIngredient = strIngredient;
        this.strDescription = strDescription;
        this.strThumbnail = strThumbnail;
    }

    public String getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(String idIngredient) {
        this.idIngredient = idIngredient;
    }

    public String getStrIngredient() {
        return strIngredient;
    }

    public void setStrIngredient(String strIngredient) {
        this.strIngredient = strIngredient;
    }

    public String getStrDescription() {
        return strDescription;
    }

    public void setStrDescription(String strDescription) {
        this.strDescription = strDescription;
    }

    public String getStrThumbnail() {
        return strThumbnail;
    }

    public void setStrThumbnail(String strThumbnail) {
        this.strThumbnail = strThumbnail;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "idIngredient='" + idIngredient + '\'' +
                ", strIngredient='" + strIngredient + '\'' +
                ", strDescription='" + strDescription + '\'' +
                ", strThumbnail='" + strThumbnail + '\'' +
                '}';
    }
}

