package eg.gov.iti.jets.foodplanner.authentication.presenter;

public interface RegisterPresenterInterface {

    public void Register(String email,String Password);

    public void googleRegister(String email,String webClientId);
}
