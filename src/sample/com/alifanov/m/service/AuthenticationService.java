package sample.com.alifanov.m.service;

import sample.com.alifanov.m.model.RegistratedUser;
import sample.com.alifanov.m.model.UserInformation;



public class AuthenticationService {

    private UserInformation userInformation;

    public RegistratedUser createUser(String login, String password){
        this.userInformation = new UserInformation();
        this.userInformation.setLogin(login);
        this. userInformation.setPassword(password);
        return new RegistratedUser(userInformation);
    }

    public UserInformation getCurrentUserInformation(){
        return new UserInformation();
    }

    public void changeUserInformation(){
    }

    public boolean userExists(String login, String password){
        if (this.userInformation.getLogin().equals(login) &&
                this.userInformation.getPassword().equals(password)){
            return true;
        }
        else return  false;
    }
}
