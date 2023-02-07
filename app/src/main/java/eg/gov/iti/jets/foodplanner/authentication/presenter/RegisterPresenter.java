package eg.gov.iti.jets.foodplanner.authentication.presenter;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import eg.gov.iti.jets.foodplanner.R;
import eg.gov.iti.jets.foodplanner.authentication.view.RegisterViewInterface;


public class RegisterPresenter implements RegisterPresenterInterface {

    private static final String TAG = "AuthPresenter";

    private FirebaseAuth firebaseAuth;

    private RegisterViewInterface regViewInterface;

    public RegisterPresenter(RegisterViewInterface regViewInterface) {
        this.regViewInterface = regViewInterface;
        firebaseAuth = FirebaseAuth.getInstance();
//        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
//        if (currentUser != null) {
//
//        }
    }

    @Override
    public void Register(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:success");
                            regViewInterface.onSuccessRegister();
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            regViewInterface.onFailRegister(task.getException().getMessage());
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception ex) {
                        Log.i(TAG, "onFailure: " + ex.getMessage());
                        ex.printStackTrace();
                        regViewInterface.onFailRegister(ex.getMessage());
                    }
                });
    }

    @Override
    public void googleRegister(String email,String webClientId) {
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder()
                .requestIdToken(webClientId)
                .requestEmail()
                .build();

        regViewInterface.callGoogleBuilder(googleSignInOptions);
    }
}
