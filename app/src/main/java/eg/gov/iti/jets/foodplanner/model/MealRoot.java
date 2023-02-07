package eg.gov.iti.jets.foodplanner.model;

import java.util.ArrayList;

public class MealRoot {
   private ArrayList<Meal> meals;

    public MealRoot(ArrayList<Meal> meals) {
        this.meals = meals;
    }
    public MealRoot(){}



    public ArrayList<Meal> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<Meal> meals) {
        this.meals = meals;
    }


    @Override
    public String toString() {
        return "MealRoot{" +
                "meals=" + meals +
                '}';
    }
}
