package eg.gov.iti.jets.foodplanner.authentication.view;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public interface LoginViewInterface {
    public void Login (String email,String password);
    public void OnLoginSuccess();

    public void onGoogleRegisterSuccess(String error);
    public void onGoogleRegisterFail(String error);

    public void callGoogleBuilder(GoogleSignInOptions googleSignInOptions);

}
