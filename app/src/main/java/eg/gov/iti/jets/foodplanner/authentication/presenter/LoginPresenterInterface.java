package eg.gov.iti.jets.foodplanner.authentication.presenter;

public interface LoginPresenterInterface {
    public void Login (String email,String password);

    public void googleRegister(String email,String webClientId);
}
