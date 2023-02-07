package eg.gov.iti.jets.foodplanner.authentication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import eg.gov.iti.jets.foodplanner.MainActivity;
import eg.gov.iti.jets.foodplanner.MySharedPref;
import eg.gov.iti.jets.foodplanner.OnSearchingActivity;
import eg.gov.iti.jets.foodplanner.R;
import eg.gov.iti.jets.foodplanner.SplashScreen;
import eg.gov.iti.jets.foodplanner.authentication.presenter.LoginPresenter;
import eg.gov.iti.jets.foodplanner.authentication.presenter.LoginPresenterInterface;

public class LoginActivity extends AppCompatActivity implements LoginViewInterface {
    ImageView animButton;
    TextView skipTv;
    MySharedPref mySharedPref;
    LoginPresenterInterface loginPresenterInterface;
    EditText login_email_edittxt;
    EditText login_password_edittxt;
    Button login_login_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();
        animButton = findViewById(R.id.login_anim_imageView);
        login_email_edittxt=findViewById(R.id.login_email_edittxt);
        login_password_edittxt=findViewById(R.id.login_password_edittxt);
        login_login_btn=findViewById(R.id.login_login_btn);
        loginPresenterInterface=new LoginPresenter(getApplicationContext(),this);
        login_login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login(login_email_edittxt.getText().toString(),login_password_edittxt.getText().toString());
            }
        });


        skipTv = findViewById(R.id.login_skip_textView);

        setupAnimationBtn();
        skipTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });
    }
    void setupAnimationBtn() {
        animButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                overridePendingTransition(R.anim.slide_left_to_right, R.anim.slide_left_to_right);
            }
        });
    }

    @Override
    public void Login(String email, String password) {
      loginPresenterInterface.Login(email,password);
    }

    @Override
    public void OnLoginSuccess() {
        Intent i=new Intent(LoginActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }

}