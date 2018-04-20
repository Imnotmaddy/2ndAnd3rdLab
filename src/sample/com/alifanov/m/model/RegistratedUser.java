package sample.com.alifanov.m.model;

public class RegistratedUser extends User {

    private UserInformation userInformation;

    public RegistratedUser() {
    }

    public RegistratedUser(UserInformation userInformation) {

        this.userInformation = userInformation;
    }

    public boolean login(){
        return false;
    }

}
