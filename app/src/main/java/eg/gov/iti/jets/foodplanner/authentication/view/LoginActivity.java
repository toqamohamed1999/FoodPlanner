package eg.gov.iti.jets.foodplanner.authentication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import eg.gov.iti.jets.foodplanner.R;

public class LoginActivity extends AppCompatActivity implements LoginViewInterface{

    ImageView animButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        animButton = findViewById(R.id.login_anim_imageView);


        setupAnimationBtn();
    }


    void setupAnimationBtn(){
        animButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                overridePendingTransition(R.anim.slide_left_to_right,R.anim.slide_left_to_right);
            }
        });
    }

    @Override
    public void Login(String email, String password) {

    }
}