package eg.gov.iti.jets.foodplanner.model;

import java.util.ArrayList;

class ingredientRoot {
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
