package eg.gov.iti.jets.foodplanner.authentication.view;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eg.gov.iti.jets.foodplanner.MainActivity;
import eg.gov.iti.jets.foodplanner.MySharedPref;
import eg.gov.iti.jets.foodplanner.R;
import eg.gov.iti.jets.foodplanner.authentication.presenter.RegisterPresenter;
import eg.gov.iti.jets.foodplanner.profile.view.ProfileActivity;

public class RegisterActivity extends AppCompatActivity implements RegisterViewInterface {

    private static final String TAG = "RegisterActivity";

    private EditText nameEditText, emailEditText, passwordEditText, confirmPasswordEditText;

    private Button registerBtn;

    private TextView haveAccountTv;

    ImageView animButton;

    private RegisterPresenter authPresenter;

    private String  email, password;

    ProgressDialog progressDialog;

    MySharedPref mySharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        initUI();
        init();
        setupAnimationBtn();
        onRegisterClick();
    }

    private void initUI() {

        emailEditText = findViewById(R.id.register_email_editText);
        passwordEditText = findViewById(R.id.register_password_editText);
        confirmPasswordEditText = findViewById(R.id.register_confirmPassword_editText);
        haveAccountTv = findViewById(R.id.register_haveAccount_textView);
        registerBtn = findViewById(R.id.register_register_btn);
        animButton = findViewById(R.id.register_anim_imageView);

        getSupportActionBar().hide();
        changeStatusBarColor();
    }

    private void init() {

        authPresenter = new RegisterPresenter(this);
        mySharedPref = new MySharedPref(getApplicationContext());
    }


    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }
    void setupAnimationBtn(){
        animButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                overridePendingTransition(R.anim.slide_right_to_left,R.anim.slide_left_to_right);
            }
        });
    }

    private void onRegisterClick() {
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = emailEditText.getText().toString();
                password = passwordEditText.getText().toString();
                if(checkDataValidation()) {
                    authPresenter.Register(email, password);
                    setupProgressDialog("Register...");
                }
            }
        });
        haveAccountTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    public void onSuccessRegister() {
        progressDialog.cancel();
        Toast.makeText(this, "Register Success", Toast.LENGTH_SHORT).show();
        mySharedPref.sharedPrefWrite(email,password);
        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void onFailRegister(String error) {
        progressDialog.cancel();
        Toast.makeText(this, "Register failed, Try again", Toast.LENGTH_SHORT).show();
    }


    private boolean validEmail(String email) {

        String emailRegex = "^.+@.+\\..+$";
        Pattern emailPattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPattern.matcher(email);
        return matcher.find();
    }

    private boolean validPassword(String password) {
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";

        Pattern passwordPattern = Pattern.compile(regex);

        if (password == null) {
            return false;
        }
        Matcher m = passwordPattern.matcher(password);

        return m.matches();

    }

    private boolean checkDataValidation(){

        if(!validEmail(email)){
            emailEditText.setError("invalid email");
            return false;
        }else if(!validPassword(password)) {
            passwordEditText.setError("password must have at least 8 characters and contains uppercase letters, lowercase letters, numbers, and symbols");
            return false;
        }else if(!passwordEditText.getText().toString().equals(confirmPasswordEditText.getText().toString())) {
            confirmPasswordEditText.setError("confirm password doesn't match your password");
            return false;
        }
        return true;
    }

    void setupProgressDialog(String message) {
        progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.setMessage(message);
        progressDialog.setTitle("Develop Meal");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(true);
        progressDialog.show();
    }
}