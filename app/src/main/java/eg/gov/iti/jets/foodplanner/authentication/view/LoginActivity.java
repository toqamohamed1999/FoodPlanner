package eg.gov.iti.jets.foodplanner.authentication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import eg.gov.iti.jets.foodplanner.R;

public class LoginActivity extends AppCompatActivity implements LoginViewInterface{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    @Override
    public void Login(String email, String password) {

    }
}