package eg.gov.iti.jets.foodplanner;



import java.util.ArrayList;

//private String idIngredient;
//private String strIngredient;
//private String strDescription;
public class Ingredient {

    private String idIngredient;
    private String strIngredient;
    private String strDescription;

    public Ingredient() {
    }

    public Ingredient(String idIngredient, String strIngredient, String strDescription) {
        this.idIngredient = idIngredient;
        this.strIngredient = strIngredient;
        this.strDescription = strDescription;
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

    @Override
    public String toString() {
        return "Ingredient{" +
                "idIngredient='" + idIngredient + '\'' +
                ", strIngredient='" + strIngredient + '\'' +
                ", strDescription='" + strDescription + '\'' +
                '}';
    }
}

class ingredientRoot{
    public ArrayList<Ingredient> ingredientList;

    public ingredientRoot() {
    }

    public ingredientRoot(ArrayList<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public ArrayList<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(ArrayList<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    @Override
    public String toString() {
        return "ingredientRoot{" +
                "ingredientList=" + ingredientList +
                '}';
    }
}
