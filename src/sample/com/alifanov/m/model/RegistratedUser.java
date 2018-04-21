package sample.com.alifanov.m.model;

import sample.com.alifanov.m.service.AuthenticationService;

public class RegistratedUser extends User {

    private UserInformation userInformation;

    public RegistratedUser() {
        this.userInformation = new UserInformation();
        this.authenticationService = new AuthenticationService();
    }

    public RegistratedUser(UserInformation userInformation) {

        this.userInformation = userInformation;
    }

    public UserInformation getUserInformation() {
        return userInformation;
    }

    public void setUserInformation(UserInformation userInformation) {
        this.userInformation = userInformation;
    }

    public boolean login(){
        return false;
    }

}
