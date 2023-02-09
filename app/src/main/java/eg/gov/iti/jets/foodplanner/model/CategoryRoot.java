package eg.gov.iti.jets.foodplanner.model;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import eg.gov.iti.jets.foodplanner.model.Category;

public class CategoryRoot {
    private ArrayList<Category> categories;

    public CategoryRoot(ArrayList<Category> categories) {
        this.categories = categories;
    }
    public CategoryRoot(){

    }

    @Override
    public String toString() {
        return "CategoryRoot{" +
                "categories=" + categories +
                '}';
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

}

