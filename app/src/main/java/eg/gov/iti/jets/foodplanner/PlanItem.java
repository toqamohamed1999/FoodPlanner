package eg.gov.iti.jets.foodplanner;


public class PlanItem {

    private String dayName;
    private String date;
    private String mealId;

    public PlanItem() {
    }

    public PlanItem(String dayName, String date, String mealId) {
        this.dayName = dayName;
        this.date = date;
        this.mealId = mealId;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMealId() {
        return mealId;
    }

    public void setMealId(String mealId) {
        this.mealId = mealId;
    }

    @Override
    public String toString() {
        return "PlanItem{" +
                "dayName='" + dayName + '\'' +
                ", date='" + date + '\'' +
                ", mealId='" + mealId + '\'' +
                '}';
    }
}
