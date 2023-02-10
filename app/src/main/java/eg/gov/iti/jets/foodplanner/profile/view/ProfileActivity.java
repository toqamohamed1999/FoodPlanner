package eg.gov.iti.jets.foodplanner.profile.view;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

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

import eg.gov.iti.jets.foodplanner.R;
import eg.gov.iti.jets.foodplanner.authentication.view.LoginActivity;

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = "ProfileActivity";

    public static final int BACKUP_CODE = 12;
    private Button logoutBtn,backupButton;

    private ImageButton backBtn;

    private TextView emailTv,userNameTV;

    private FirebaseAuth firebaseAuth;


    private LottieAnimationView lottieAnimationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();


        initUI();
        setUpLottieAnimationView();
        logout();
        handleSaveBackupToFirebase();
    }

    private void initUI(){
        lottieAnimationView = findViewById(R.id.profile_animation_view);
        userNameTV = findViewById(R.id.profile_userName_textView);
        emailTv = findViewById(R.id.profile_email_textView);
        logoutBtn = findViewById(R.id.profile_logout_button);
        backupButton = findViewById(R.id.profile_backup_button);
        backBtn = findViewById(R.id.profile_back_imageBtn);
    }

    private void setUpLottieAnimationView(){
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
        animator.addUpdateListener(animation -> {
            lottieAnimationView.setProgress((Float) animation.getAnimatedValue());
        });
        animator.start();
    }

    private void handleSaveBackupToFirebase(){
        backupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void logout(){
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

    }




//    private void restoreDBIntent() {
//        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
//        i.setType("*/*");
//        startActivityForResult(Intent.createChooser(i, "Select DB File"), BACKUP_CODE);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == BACKUP_CODE && resultCode == RESULT_OK && data != null) {
//            Uri fileUri = data.getData();
//
//            try {
//                assert fileUri != null;
//                InputStream inputStream = null;
//                try {
//                    inputStream = getContentResolver().openInputStream(fileUri);
//                } catch (FileNotFoundException e) {
//                    throw new RuntimeException(e);
//                }
//                if (validFile(fileUri)) {
//                    restoreDatabase(inputStream);
//                } else {
//                    Utils.showSnackbar(findViewById(android.R.id.content), getString(R.string.restore_failed), 1);
//                }
//                if (inputStream != null) {
//                    try {
//                        inputStream.close();
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private boolean validFile(Uri fileUri) {
//        ContentResolver cr = this.getContentResolver();
//        String mime = cr.getType(fileUri);
//        return "application/octet-stream".equals(mime);
//    }
//
//
//    public static void backupDatabaseForRestore(Activity activity, Context context) {
//        File dbfile = activity.getDatabasePath("mydatabase");
//        File sdir = new File(getFilePath(context, 0), "backup");
//        String sfpath = sdir.getPath() + File.separator + BACKUP_RESTORE_ROLLBACK_FILE_NAME;
//        if (!sdir.exists()) {
//            sdir.mkdirs();
//        }
//        File savefile = new File(sfpath);
//        if (savefile.exists()) {
//            savefile.delete();
//        }
//        try {
//            if (savefile.createNewFile()) {
//                int buffersize = 8 * 1024;
//                byte[] buffer = new byte[buffersize];
//                int bytes_read = buffersize;
//                OutputStream savedb = new FileOutputStream(sfpath);
//                InputStream indb = new FileInputStream(dbfile);
//                while ((bytes_read = indb.read(buffer, 0, buffersize)) > 0) {
//                    savedb.write(buffer, 0, bytes_read);
//                }
//                savedb.flush();
//                indb.close();
//                savedb.close();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//    }
}