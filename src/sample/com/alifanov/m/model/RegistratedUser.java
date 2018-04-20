package sample.com.alifanov.m.model;

public class RegistratedUser extends User {
    public boolean login(){
        return false;
    }

    public UserInformation getSelfInformation(){
        return new UserInformation();
    }

}
