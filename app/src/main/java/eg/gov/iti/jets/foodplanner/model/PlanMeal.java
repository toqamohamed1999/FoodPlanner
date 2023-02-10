package eg.gov.iti.jets.foodplanner.model;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "planMeal",primaryKeys = {"weekDay", "strMeal"})
public class PlanMeal implements Serializable {

    @NonNull
    private String weekDay;
    private String idMeal;
    @NonNull
    private String strMeal;
    private String strDrinkAlternate;
    private String strCategory;
    private String strArea;
    private String strInstructions;
    private String strMealThumb;
    private String strTags;
    private String strYoutube;
    private String strIngredient1;
    private String strIngredient2;
    private String strIngredient3;
    private String strIngredient4;
    private String strIngredient5;
    private String strIngredient6;
    private String strIngredient7;
    private String strIngredient8;
    private String strIngredient9;
    private String strIngredient10;
    private String strIngredient11;
    private String strIngredient12;
    private String strIngredient13;
    private String strIngredient14;
    private String strIngredient15;
    private String strIngredient16;
    private String strIngredient17;
    private String strIngredient18;
    private String strIngredient19;
    private String strIngredient20;

    public PlanMeal() {
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public String getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(String idMeal) {
        this.idMeal = idMeal;
    }

    public String getStrMeal() {
        return strMeal;
    }

    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }

    public String getStrDrinkAlternate() {
        return strDrinkAlternate;
    }

    public void setStrDrinkAlternate(String strDrinkAlternate) {
        this.strDrinkAlternate = strDrinkAlternate;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }

    public String getStrArea() {
        return strArea;
    }

    public void setStrArea(String strArea) {
        this.strArea = strArea;
    }

    public String getStrInstructions() {
        return strInstructions;
    }

    public void setStrInstructions(String strInstructions) {
        this.strInstructions = strInstructions;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public void setStrMealThumb(String strMealThumb) {
        this.strMealThumb = strMealThumb;
    }

    public String getStrTags() {
        return strTags;
    }

    public void setStrTags(String strTags) {
        this.strTags = strTags;
    }

    public String getStrYoutube() {
        return strYoutube;
    }

    public void setStrYoutube(String strYoutube) {
        this.strYoutube = strYoutube;
    }

    public String getStrIngredient1() {
        return strIngredient1;
    }

    public void setStrIngredient1(String strIngredient1) {
        this.strIngredient1 = strIngredient1;
    }

    public String getStrIngredient2() {
        return strIngredient2;
    }

    public void setStrIngredient2(String strIngredient2) {
        this.strIngredient2 = strIngredient2;
    }

    public String getStrIngredient3() {
        return strIngredient3;
    }

    public void setStrIngredient3(String strIngredient3) {
        this.strIngredient3 = strIngredient3;
    }

    public String getStrIngredient4() {
        return strIngredient4;
    }

    public void setStrIngredient4(String strIngredient4) {
        this.strIngredient4 = strIngredient4;
    }

    public String getStrIngredient5() {
        return strIngredient5;
    }

    public void setStrIngredient5(String strIngredient5) {
        this.strIngredient5 = strIngredient5;
    }

    public String getStrIngredient6() {
        return strIngredient6;
    }

    public void setStrIngredient6(String strIngredient6) {
        this.strIngredient6 = strIngredient6;
    }

    public String getStrIngredient7() {
        return strIngredient7;
    }

    public void setStrIngredient7(String strIngredient7) {
        this.strIngredient7 = strIngredient7;
    }

    public String getStrIngredient8() {
        return strIngredient8;
    }

    public void setStrIngredient8(String strIngredient8) {
        this.strIngredient8 = strIngredient8;
    }

    public String getStrIngredient9() {
        return strIngredient9;
    }

    public void setStrIngredient9(String strIngredient9) {
        this.strIngredient9 = strIngredient9;
    }

    public String getStrIngredient10() {
        return strIngredient10;
    }

    public void setStrIngredient10(String strIngredient10) {
        this.strIngredient10 = strIngredient10;
    }

    public String getStrIngredient11() {
        return strIngredient11;
    }

    public void setStrIngredient11(String strIngredient11) {
        this.strIngredient11 = strIngredient11;
    }

    public String getStrIngredient12() {
        return strIngredient12;
    }

    public void setStrIngredient12(String strIngredient12) {
        this.strIngredient12 = strIngredient12;
    }

    public String getStrIngredient13() {
        return strIngredient13;
    }

    public void setStrIngredient13(String strIngredient13) {
        this.strIngredient13 = strIngredient13;
    }

    public String getStrIngredient14() {
        return strIngredient14;
    }

    public void setStrIngredient14(String strIngredient14) {
        this.strIngredient14 = strIngredient14;
    }

    public String getStrIngredient15() {
        return strIngredient15;
    }

    public void setStrIngredient15(String strIngredient15) {
        this.strIngredient15 = strIngredient15;
    }

    public String getStrIngredient16() {
        return strIngredient16;
    }

    public void setStrIngredient16(String strIngredient16) {
        this.strIngredient16 = strIngredient16;
    }

    public String getStrIngredient17() {
        return strIngredient17;
    }

    public void setStrIngredient17(String strIngredient17) {
        this.strIngredient17 = strIngredient17;
    }

    public String getStrIngredient18() {
        return strIngredient18;
    }

    public void setStrIngredient18(String strIngredient18) {
        this.strIngredient18 = strIngredient18;
    }

    public String getStrIngredient19() {
        return strIngredient19;
    }

    public void setStrIngredient19(String strIngredient19) {
        this.strIngredient19 = strIngredient19;
    }

    public String getStrIngredient20() {
        return strIngredient20;
    }

    public void setStrIngredient20(String strIngredient20) {
        this.strIngredient20 = strIngredient20;
    }

    @Override
    public String toString() {
        return "PlanItem{" +
                "weekDay='" + weekDay + '\'' +
                ", idMeal='" + idMeal + '\'' +
                ", strMeal='" + strMeal + '\'' +
                ", strDrinkAlternate='" + strDrinkAlternate + '\'' +
                ", strCategory='" + strCategory + '\'' +
                ", strArea='" + strArea + '\'' +
                ", strInstructions='" + strInstructions + '\'' +
                ", strMealThumb='" + strMealThumb + '\'' +
                ", strTags='" + strTags + '\'' +
                ", strYoutube='" + strYoutube + '\'' +
                ", strIngredient1='" + strIngredient1 + '\'' +
                ", strIngredient2='" + strIngredient2 + '\'' +
                ", strIngredient3='" + strIngredient3 + '\'' +
                ", strIngredient4='" + strIngredient4 + '\'' +
                ", strIngredient5='" + strIngredient5 + '\'' +
                ", strIngredient6='" + strIngredient6 + '\'' +
                ", strIngredient7='" + strIngredient7 + '\'' +
                ", strIngredient8='" + strIngredient8 + '\'' +
                ", strIngredient9='" + strIngredient9 + '\'' +
                ", strIngredient10='" + strIngredient10 + '\'' +
                ", strIngredient11='" + strIngredient11 + '\'' +
                ", strIngredient12='" + strIngredient12 + '\'' +
                ", strIngredient13='" + strIngredient13 + '\'' +
                ", strIngredient14='" + strIngredient14 + '\'' +
                ", strIngredient15='" + strIngredient15 + '\'' +
                ", strIngredient16='" + strIngredient16 + '\'' +
                ", strIngredient17='" + strIngredient17 + '\'' +
                ", strIngredient18='" + strIngredient18 + '\'' +
                ", strIngredient19='" + strIngredient19 + '\'' +
                ", strIngredient20='" + strIngredient20 + '\'' +
                '}';
    }
    public PlanMeal getPlanMealFromMeal(Meal meal,String day){
        PlanMeal planMeal=new PlanMeal();
        planMeal.setWeekDay(day);
        planMeal.setIdMeal(meal.getIdMeal());
        planMeal.setStrArea(meal.getStrArea());
        planMeal.setStrMeal(meal.getStrMeal());
        planMeal.setStrCategory(meal.getStrCategory());
        planMeal.setStrDrinkAlternate(meal.getStrDrinkAlternate());
        planMeal.setStrInstructions(meal.getStrInstructions());
        planMeal.setStrMealThumb(meal.getStrMealThumb());
        planMeal.setStrTags(meal.getStrTags());
        planMeal.setStrYoutube(meal.getStrYoutube());
        planMeal.setStrIngredient1(meal.getStrIngredient1());
        planMeal.setStrIngredient2(meal.getStrIngredient2());
        planMeal.setStrIngredient3(meal.getStrIngredient3());
        planMeal.setStrIngredient4(meal.getStrIngredient4());
        planMeal.setStrIngredient5(meal.getStrIngredient5());
        planMeal.setStrIngredient6(meal.getStrIngredient6());
        planMeal.setStrIngredient7(meal.getStrIngredient7());
        planMeal.setStrIngredient8(meal.getStrIngredient8());
        planMeal.setStrIngredient9(meal.getStrIngredient9());
        planMeal.setStrIngredient10(meal.getStrIngredient10());
        planMeal.setStrIngredient11(meal.getStrIngredient11());
        planMeal.setStrIngredient12(meal.getStrIngredient12());
        planMeal.setStrIngredient13(meal.getStrIngredient13());
        planMeal.setStrIngredient14(meal.getStrIngredient14());
        planMeal.setStrIngredient15(meal.getStrIngredient15());
        planMeal.setStrIngredient16(meal.getStrIngredient16());
        planMeal.setStrIngredient17(meal.getStrIngredient17());
        planMeal.setStrIngredient18(meal.getStrIngredient18());
        planMeal.setStrIngredient19(meal.getStrIngredient19());
        planMeal.setStrIngredient20(meal.getStrIngredient20());

        return planMeal;

    }

    public Meal getMealFromMealPlanMeal(PlanMeal planMeal){
        Meal meal=new Meal();
        meal.setIdMeal(planMeal.getIdMeal());
        meal.setStrArea(planMeal.getStrArea());
        meal.setStrMeal(planMeal.getStrMeal());
        meal.setStrCategory(planMeal.getStrCategory());
        meal.setStrDrinkAlternate(planMeal.getStrDrinkAlternate());
        meal.setStrInstructions(planMeal.getStrInstructions());
        meal.setStrMealThumb(planMeal.getStrMealThumb());
        meal.setStrTags(planMeal.getStrTags());
        meal.setStrYoutube(planMeal.getStrYoutube());
        meal.setStrIngredient1(planMeal.getStrIngredient1());
        meal.setStrIngredient2(planMeal.getStrIngredient2());
        meal.setStrIngredient3(planMeal.getStrIngredient3());
        meal.setStrIngredient4(planMeal.getStrIngredient4());
        meal.setStrIngredient5(planMeal.getStrIngredient5());
        meal.setStrIngredient6(planMeal.getStrIngredient6());
        meal.setStrIngredient7(planMeal.getStrIngredient7());
        meal.setStrIngredient8(planMeal.getStrIngredient8());
        meal.setStrIngredient9(planMeal.getStrIngredient9());
        meal.setStrIngredient10(planMeal.getStrIngredient10());
        meal.setStrIngredient11(planMeal.getStrIngredient11());
        meal.setStrIngredient12(planMeal.getStrIngredient12());
        meal.setStrIngredient13(planMeal.getStrIngredient13());
        meal.setStrIngredient14(planMeal.getStrIngredient14());
        meal.setStrIngredient15(planMeal.getStrIngredient15());
        meal.setStrIngredient16(planMeal.getStrIngredient16());
        meal.setStrIngredient17(planMeal.getStrIngredient17());
        meal.setStrIngredient18(planMeal.getStrIngredient18());
        meal.setStrIngredient19(planMeal.getStrIngredient19());
        meal.setStrIngredient20(planMeal.getStrIngredient20());

        return meal;

    }
}
