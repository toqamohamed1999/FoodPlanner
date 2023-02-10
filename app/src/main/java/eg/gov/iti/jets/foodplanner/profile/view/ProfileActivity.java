package eg.gov.iti.jets.foodplanner.profile.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.utils.Utils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import eg.gov.iti.jets.foodplanner.MyDialog;
import eg.gov.iti.jets.foodplanner.MySharedPref;
import eg.gov.iti.jets.foodplanner.R;
import eg.gov.iti.jets.foodplanner.authentication.view.LoginActivity;
import eg.gov.iti.jets.foodplanner.database.LocalSource;
import eg.gov.iti.jets.foodplanner.homeScreen.presenter.HomePresenter;
import eg.gov.iti.jets.foodplanner.model.Meal;
import eg.gov.iti.jets.foodplanner.model.Repo;
import eg.gov.iti.jets.foodplanner.model.User;
import eg.gov.iti.jets.foodplanner.network.RemoteSource;
import eg.gov.iti.jets.foodplanner.profile.presenter.ProfilePresenter;

public class ProfileActivity extends AppCompatActivity implements ProfileViewInterface{


    private static final String TAG = "ProfileActivity";

    private Button logoutBtn, backupButton,restoreBackupBtn;

    private ImageButton backBtn;

    private TextView emailTv, userNameTV;

    private FirebaseAuth firebaseAuth;
    MySharedPref mySharedPref;

    private LottieAnimationView lottieAnimationView;

    private ProfilePresenter profilePresenter;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();



        initUI();
        setUpLottieAnimationView();
        setupUserNameAndEmail();
        setUPPresenter();
        logout();
        handleSaveBackupToFirebase();
        handleRestoreBackupToFirebase();
        handelBackButton();
    }

    private void initUI() {
        lottieAnimationView = findViewById(R.id.profile_animation_view);
        userNameTV = findViewById(R.id.profile_userName_textView);
        emailTv = findViewById(R.id.profile_email_textView);
        logoutBtn = findViewById(R.id.profile_logout_button);
        backupButton = findViewById(R.id.profile_backup_button);
        backBtn = findViewById(R.id.profile_back_imageBtn);
        restoreBackupBtn = findViewById(R.id.profile_restore_backup_button);
        mySharedPref = new MySharedPref(getApplicationContext());
        firebaseAuth = FirebaseAuth.getInstance();
    }

    void setUPPresenter(){
        profilePresenter = new ProfilePresenter(this, Repo.getInstance(getApplicationContext(), LocalSource.getLocalSource(getApplicationContext()), RemoteSource.getRemoteSource()));
    }

    private void setUpLottieAnimationView() {
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
        animator.addUpdateListener(animation -> {
            lottieAnimationView.setProgress((Float) animation.getAnimatedValue());
        });
        animator.start();
    }

    private void handelBackButton(){
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void logout() {
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = MyDialog.myDialog(ProfileActivity.this);
                builder.setMessage("Do you want to logout ?");

                builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                    // finish();
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    Log.i(TAG, "onClick: " + user);
                    if (user != null) {
                        mySharedPref.sharedPrefWrite("not found", "not found");
                        firebaseAuth.signOut();
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        finish();
                    }
                });

                builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                    dialog.cancel();
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    void setupUserNameAndEmail() {
        User user = mySharedPref.sharedPrefRead();
        if (!user.getEmail().equals("not found") && !user.getPassword().equals("not found")) {
            emailTv.setText(user.getEmail());
            String[] arr = user.getEmail().split("@");
            userNameTV.setText(arr[0]);
        }
    }

    void setupProgressDialog(String message) {
        progressDialog = new ProgressDialog(ProfileActivity.this);
        progressDialog.setMessage(message);
        progressDialog.setTitle("Backup Loading..");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(true);
        progressDialog.show();
    }

    private void handleSaveBackupToFirebase(){
        backupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profilePresenter.storeMealsToFirebase();
                setupProgressDialog("Save Backup Data");
            }
        });
    }

    private void handleRestoreBackupToFirebase(){
        restoreBackupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profilePresenter.getMealsFromFirebase();
                setupProgressDialog("Restore Your Backup Data");
            }
        });
    }

    @Override
    public void onResultGetMealsFromFirebase(String message) {
        if(message.contains("success")){
            Toast.makeText(this, "Restore Your Backup success", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Restore Your Backup failed,Maybe you have no internet connection", Toast.LENGTH_SHORT).show();
        }
        progressDialog.cancel();
    }

    @Override
    public void onResultStoreMealsToFirebase(String message) {
        if(message.contains("success")){
            Toast.makeText(this, "Backup Data Successfully", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Backup Data Failed,Maybe you have no internet connection", Toast.LENGTH_SHORT).show();
        }
        progressDialog.cancel();
    }
}