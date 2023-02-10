package eg.gov.iti.jets.foodplanner.authentication.presenter;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import eg.gov.iti.jets.foodplanner.MainActivity;
import eg.gov.iti.jets.foodplanner.MySharedPref;
import eg.gov.iti.jets.foodplanner.authentication.view.LoginActivity;
import eg.gov.iti.jets.foodplanner.authentication.view.LoginViewInterface;

public class LoginPresenter implements LoginPresenterInterface {
    private static final String TAG = "LoginPresenter";
    LoginViewInterface loginViewInterface;
    private FirebaseAuth firebaseAuth;
    private Context context;
    MySharedPref mySharedPref;

    public LoginPresenter(Context context, LoginViewInterface _loginViewInterfac) {
        this.loginViewInterface = _loginViewInterfac;
        this.context = context;
        firebaseAuth = FirebaseAuth.getInstance();
        mySharedPref = new MySharedPref(context);

    }

    @Override
    public void Login(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.i(TAG, "signInWithEmail:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            if(user.isEmailVerified()) {
                                mySharedPref.sharedPrefWrite(email, password);
                                loginViewInterface.OnLoginSuccess();
                            }
                            else {
                                Toast.makeText(context, "Not a Valid Email!", Toast.LENGTH_LONG).show();
                            }

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.i(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(context, "Login failed! Please Check Your Email And Password.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void googleRegister(String email,String webClientId) {
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder()
                .requestIdToken(webClientId)
                .requestEmail()
                .build();

        loginViewInterface.callGoogleBuilder(googleSignInOptions);
    }


}
