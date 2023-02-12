package eg.gov.iti.jets.foodplanner.OnBoarding;

public class ScreenBoardingItem {
    String Title,Description;
    int ScreenImg;

    public ScreenBoardingItem(String title, String description, int screenImg) {
        Title = title;
        Description = description;
        ScreenImg = screenImg;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setScreenImg(int screenImg) {
        ScreenImg = screenImg;
    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public int getScreenImg() {
        return ScreenImg;
    }

    @Override
    public String toString() {
        return "ScreenItem{" +
                "Title='" + Title + '\'' +
                ", Description='" + Description + '\'' +
                ", ScreenImg=" + ScreenImg +
                '}';
    }
}
