package eg.gov.iti.jets.foodplanner.authentication.view;

import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import eg.gov.iti.jets.foodplanner.MainActivity;
import eg.gov.iti.jets.foodplanner.MySharedPref;
import eg.gov.iti.jets.foodplanner.R;
import eg.gov.iti.jets.foodplanner.authentication.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements LoginViewInterface {
    ImageView animButton;
    TextView skipTv,login_registerNow_txtview;
    MySharedPref mySharedPref;
    LoginPresenter loginPresenter;
    EditText login_email_editTxt;
    EditText login_password_editTxt;
    Button login_login_btn;
    ImageView login_google_imageView;

    String email ;

    private GoogleSignInClient googleSignInClient;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();


        initUI();
        handleLoginBtn();
        handleSkipEvent();
        setupAnimationBtn();
        onGoogleClick();
    }

    private void initUI(){
        animButton = findViewById(R.id.login_anim_imageView);
        login_email_editTxt =findViewById(R.id.login_email_edittxt);
        login_password_editTxt =findViewById(R.id.login_password_edittxt);
        login_login_btn=findViewById(R.id.login_login_btn);
        login_registerNow_txtview=findViewById(R.id.login_registerNow_txtview);
        login_google_imageView = findViewById(R.id.login_google_imageView);
        skipTv = findViewById(R.id.login_skip_textView);
        login_registerNow_txtview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });

        loginPresenter=new LoginPresenter(getApplicationContext(),this);
    }

    private void handleSkipEvent(){
        skipTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Enter as a Guest", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });
    }

    private void handleLoginBtn(){
        login_login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login(login_email_editTxt.getText().toString(), login_password_editTxt.getText().toString());
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
        loginPresenter.Login(email,password);
    }

    @Override
    public void OnLoginSuccess() {
        Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();
        Intent i=new Intent(LoginActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }
///////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void onGoogleRegisterSuccess(String error) {

    }

    @Override
    public void onGoogleRegisterFail(String error) {

    }

    private void onGoogleClick() {
        login_google_imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = login_email_editTxt.getText().toString();
                String webClientId = getString(R.string.default_web_client_id);
                loginPresenter.googleRegister(email, webClientId);
            }
        });
    }

    @Override
    public void callGoogleBuilder(GoogleSignInOptions googleSignInOptions) {
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
        startGoogleSignInIntent();
    }

    public void startGoogleSignInIntent() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        someActivityResultLauncher.launch(signInIntent);
    }

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // intent
                        Intent data = result.getData();
                        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                        try {
                            GoogleSignInAccount account = task.getResult(ApiException.class);
                            firebaseAuthWithGoogle(account);
                        } catch (ApiException e) {
                            Log.w(TAG, "Google login failed", e);
                        }
                    }
                }
            });

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)

                .addOnSuccessListener(this, authResult -> {
                    Toast.makeText(this, "Google login success", Toast.LENGTH_SHORT).show();
                   /////////// mySharedPref.sharedPrefWrite(email,"");
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                })
                .addOnFailureListener(this, e -> Toast.makeText(LoginActivity.this, "Google login failed.",
                        Toast.LENGTH_SHORT).show());
        Log.i(TAG, "firebaseAuthWithGoogle: fail");
    }


}