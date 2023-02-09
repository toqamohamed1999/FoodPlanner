package eg.gov.iti.jets.foodplanner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import eg.gov.iti.jets.foodplanner.authentication.view.LoginActivity;

public class ProfileActivity extends AppCompatActivity {

    private LottieAnimationView lottieAnimationView;
    Button logoutBtn;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();
        lottieAnimationView = findViewById(R.id.profile_animation_view);
        logoutBtn = findViewById(R.id.profile_logout_button);
        firebaseAuth = FirebaseAuth.getInstance();
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                     final AlertDialog.Builder alert = new AlertDialog.Builder(getApplicationContext());
//                     alert.setTitle("Logout");
//                     alert.setMessage("Are you sure you wish to logout?")
//                             .setCancelable(false)
//                             .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                                 @Override
//                                 public void onClick(DialogInterface dialogInterface, int i) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    firebaseAuth.signOut();
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
//                                     }
//                                 }
//                             })
//                             .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                                 @Override
//                                 public void onClick(DialogInterface dialogInterface, int i) {
//
//                                 }
//                             });
//                     alert.show();
                }
            }
        });

        setUpLottieAnimationView();
    }

    private void setUpLottieAnimationView() {
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
        animator.addUpdateListener(animation -> {
            lottieAnimationView.setProgress((Float) animation.getAnimatedValue());
        });
        animator.start();
    }
}