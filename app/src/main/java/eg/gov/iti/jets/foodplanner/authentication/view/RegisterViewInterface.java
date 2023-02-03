package eg.gov.iti.jets.foodplanner.authentication.view;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public interface RegisterViewInterface {

    public void onSuccessRegister();
    public void onFailRegister(String error);

    public void onGoogleRegisterSuccess(String error);
    public void onGoogleRegisterFail(String error);

    public void callGoogleBuilder(GoogleSignInOptions googleSignInOptions);
}
