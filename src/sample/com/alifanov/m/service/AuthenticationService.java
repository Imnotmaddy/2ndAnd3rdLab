package sample.com.alifanov.m.service;

import sample.com.alifanov.m.model.User;
import sample.com.alifanov.m.model.UserInformation;

public class AuthenticationService {
    public boolean isRegistrated(){
        return false;
    }

    public User createUser(){
        return new User();
    }

    public UserInformation getCurrentUserInformation(){
        return new UserInformation();
    }

    public void changeUserInformation(){
    }
}
