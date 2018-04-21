package sample.com.alifanov.m.service;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.com.alifanov.m.model.RegistratedUser;
import sample.com.alifanov.m.model.UserInformation;


public class AuthenticationService {

    private UserInformation userInformation;

    public RegistratedUser createUser(String login, String password) {
        this.userInformation = new UserInformation();
        this.userInformation.setLogin(login);
        this.userInformation.setPassword(password);
        return new RegistratedUser(userInformation);
    }

    public UserInformation getCurrentUserInformation() {
        if (this.userInformation == null) return new UserInformation();
        return this.userInformation;
    }

    public void showUserInfo(RegistratedUser registratedUser) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL); //block user interactions until this windows is handled
        window.setTitle("INFORMATION");
        window.setWidth(250);

        Label nameLabel = new Label("Your Name: " + registratedUser.getUserInformation().getName());
        Label loginLabel = new Label("Your login: " + registratedUser.getUserInformation().getLogin());
        Label nicknameLabel = new Label("Your nickName: " + registratedUser.getNickName());
        Label emailLabel = new Label("Your email: " + registratedUser.getEmail());

        Button closeButton = new Button("close");
        closeButton.setOnAction(event -> window.close());

        VBox layout = new VBox(10);

        layout.getChildren().addAll(nameLabel, loginLabel, nicknameLabel, emailLabel, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

    public void changeUserInformation(RegistratedUser registratedUser, String login, String name, String nickName, String email) {
        registratedUser.setEmail(email);
        registratedUser.setNickName(nickName);
        registratedUser.getUserInformation().setLogin(login);
        registratedUser.getUserInformation().setName(name);
    }

    public boolean userExists(String login, String password) {
        if (this.userInformation.getLogin().equals(login) &&
                this.userInformation.getPassword().equals(password)) {
            return true;
        } else return false;
    }
}
