package eg.gov.iti.jets.foodplanner.authentication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import eg.gov.iti.jets.foodplanner.MainActivity;
import eg.gov.iti.jets.foodplanner.OnSearchingActivity;
import eg.gov.iti.jets.foodplanner.R;

public class LoginActivity extends AppCompatActivity implements LoginViewInterface{

    ImageView animButton;

    TextView skipTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        animButton = findViewById(R.id.login_anim_imageView);
        skipTv = findViewById(R.id.login_skip_textView);


        setupAnimationBtn();

        skipTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });
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