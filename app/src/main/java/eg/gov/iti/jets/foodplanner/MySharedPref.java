package eg.gov.iti.jets.foodplanner;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPref {
    public static final String PREF_NAME = "PREF";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    Context context;

    public MySharedPref(Context context) {
        this.context = context;
    }

    public void sharedPrefWrite(String userName, String password){
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(USERNAME,userName);
        editor.putString(PASSWORD, password);
        editor.commit();
    }
    public User sharedPrefRead(){
        User user=new User();
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        user.setEmail(pref.getString(USERNAME, "not found"));
        user.setPassword(pref.getString(PASSWORD, "not found"));
        return user;
    }
}
