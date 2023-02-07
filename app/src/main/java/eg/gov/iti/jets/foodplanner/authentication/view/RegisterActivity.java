package eg.gov.iti.jets.foodplanner.authentication.view;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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

import eg.gov.iti.jets.foodplanner.R;
import eg.gov.iti.jets.foodplanner.authentication.presenter.RegisterPresenter;

public class RegisterActivity extends AppCompatActivity implements RegisterViewInterface {

    private static final String TAG = "RegisterActivity";

    private EditText nameEditText, emailEditText, passwordEditText, confirmPasswordEditText;

    private Button registerBtn;

    private ImageView googleImageView;

    private TextView haveAccountTv;

    ImageView animButton;

    private RegisterPresenter authPresenter;

    private String userName, email, password;

    private GoogleSignInClient googleSignInClient;
    public static int RC_SIGN_IN = 1001;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        initUI();
        init();
        setupAnimationBtn();
        onRegisterClick();
        onGoogleClick();
    }

    private void initUI() {

        emailEditText = findViewById(R.id.register_email_editText);
        passwordEditText = findViewById(R.id.register_password_editText);
        confirmPasswordEditText = findViewById(R.id.register_confirmPassword_editText);
        googleImageView = findViewById(R.id.register_google_ImageView);
        haveAccountTv = findViewById(R.id.register_haveAccount_textView);
        registerBtn = findViewById(R.id.register_register_btn);
        animButton = findViewById(R.id.register_anim_imageView);

        getSupportActionBar().hide();
        changeStatusBarColor();
    }

    private void init() {
        authPresenter = new RegisterPresenter(this);
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
                authPresenter.Register(userName, email, password);
            }
        });
    }

    private void onGoogleClick() {
        googleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = emailEditText.getText().toString();
                String webClientId = getString(R.string.default_web_client_id);
                authPresenter.googleRegister(email, webClientId);
            }
        });
    }


    @Override
    public void onSuccessRegister() {
        Toast.makeText(this, "Register success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailRegister(String error) {
        Toast.makeText(this, "Register failed, Try again", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGoogleRegisterSuccess(String error) {
        Toast.makeText(this, "Google Register success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGoogleRegisterFail(String error) {
        Toast.makeText(this, "Google Register fail", Toast.LENGTH_SHORT).show();
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
                            Log.w(TAG, "Google sign in failed", e);
                        }
                    }
                }
            });



    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)

                .addOnSuccessListener(this, authResult -> {
                    Toast.makeText(this, "Google Register success", Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "firebaseAuthWithGoogle: success");
                    //   startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                    //   finish();
                })
                .addOnFailureListener(this, e -> Toast.makeText(RegisterActivity.this, "Google Register failed.",
                        Toast.LENGTH_SHORT).show());
        Log.i(TAG, "firebaseAuthWithGoogle: fail");
    }
}