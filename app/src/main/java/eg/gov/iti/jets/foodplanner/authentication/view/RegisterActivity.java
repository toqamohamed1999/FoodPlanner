package eg.gov.iti.jets.foodplanner.authentication.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;


import eg.gov.iti.jets.foodplanner.R;
import eg.gov.iti.jets.foodplanner.authentication.presenter.AuthPresenter;

public class RegisterActivity extends AppCompatActivity implements AuthViewInterface{

    private static final String TAG = "RegisterActivity";

    private AuthPresenter authPresenter;

    private String email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initUI();
        init();
    }

    private void initUI(){
        getSupportActionBar().hide();
        changeStatusBarColor();
    }

    private void init(){
        authPresenter = new AuthPresenter(getApplicationContext(),this);
    }


    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }


    @Override
    public void Register(String userName, String email, String Password) {
        authPresenter.Register(userName,email,password);
    }
}