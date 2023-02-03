package eg.gov.iti.jets.foodplanner.authentication.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import eg.gov.iti.jets.foodplanner.authentication.view.LoginActivity;
import eg.gov.iti.jets.foodplanner.authentication.view.LoginViewInterface;

public class LoginPresenter implements LoginPresenterInterface{
    private static final String TAG = "LoginPresenter";
    LoginViewInterface loginViewInterface;
    private FirebaseAuth firebaseAuth;
    private Context context;

    public LoginPresenter( Context context,LoginViewInterface loginViewInterfac) {
        this.loginViewInterface = loginViewInterface;
        this.context = context;
        firebaseAuth = FirebaseAuth.getInstance();
    }


    @Override
    public void Login(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(context, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                           // updateUI(null);
                        }
                    }
                });

    }
}
