package eg.gov.iti.jets.foodplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import eg.gov.iti.jets.foodplanner.authentication.view.LoginActivity;
import eg.gov.iti.jets.foodplanner.model.User;

public class SplashScreen extends AppCompatActivity {
    MySharedPref mySharedPref;
    private static final String TAG = "SplashScreen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mySharedPref=new MySharedPref(this);
        User user=mySharedPref.sharedPrefRead();

        getSupportActionBar().hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG,user.getEmail()+"User Email ");
                if(user.getEmail()=="not found"){
                    Intent i=new Intent(SplashScreen.this, LoginActivity.class);
                    startActivity(i);
                }
                else {
                    Intent i=new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(i);
                }
                finish();
            }
        },3000);
    }
}