package sample.com.alifanov.m.model;


import sample.com.alifanov.m.service.AuthenticationService;

public class User extends AbstractUser {

    public boolean Registrate(String login, String password) {
        if (authenticationService == null) authenticationService = new AuthenticationService();
        if (authenticationService.createUser(login, password) == null) {
            return false;
        }
        else {
            return true;
        }
    }

    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }

    public void setAuthenticationService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
}
