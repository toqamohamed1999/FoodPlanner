package eg.gov.iti.jets.foodplanner;

import java.util.ArrayList;

public class Category {

    private String idCategory;
    private String strCategory;
    private String strCategoryThumb;
    private String strCategoryDescription;

    public Category(String idCategory, String strCategory, String strCategoryThumb, String strCategoryDescription) {
        this.idCategory = idCategory;
        this.strCategory = strCategory;
        this.strCategoryThumb = strCategoryThumb;
        this.strCategoryDescription = strCategoryDescription;
    }
    public Category(){

    }

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }

    public String getStrCategoryThumb() {
        return strCategoryThumb;
    }

    public void setStrCategoryThumb(String strCategoryThumb) {
        this.strCategoryThumb = strCategoryThumb;
    }

    public String getStrCategoryDescription() {
        return strCategoryDescription;
    }

    public void setStrCategoryDescription(String strCategoryDescription) {
        this.strCategoryDescription = strCategoryDescription;
    }

    @Override
    public String toString() {
        return "Category{" +
                "idCategory='" + idCategory + '\'' +
                ", strCategory='" + strCategory + '\'' +
                ", strCategoryThumb='" + strCategoryThumb + '\'' +
                ", strCategoryDescription='" + strCategoryDescription + '\'' +
                '}';
    }
}

class CategoryRoot {
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

