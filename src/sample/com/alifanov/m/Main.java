package sample.com.alifanov.m;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.com.alifanov.m.model.RegistratedUser;
import sample.com.alifanov.m.model.User;

public class Main extends Application {

    private Scene authenticationScene, isLoggedScene;
    private User user = new User();
    private RegistratedUser registratedUser;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setResizable(false);
        BorderPane root = new BorderPane();

//------------------DEFAULT SCENE--------------------------------
        authenticationScene = new Scene(root, 330, 90);

        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(5));
        gridpane.setHgap(5);
        gridpane.setVgap(5);

        for (int i = 0; i < 1; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(100 / 3.0);
            gridpane.getColumnConstraints().add(column);
        }

        Label label = new Label("Register");
        gridpane.add(label, 0, 0);

        TextField loginField = new TextField();
        loginField.setPromptText("login");
        gridpane.add(loginField, 0, 1);

        TextField passwordField = new TextField();
        passwordField.setPromptText("password");
        gridpane.add(passwordField, 1, 1);

        Button loginButton = new Button("Login");
        gridpane.add(loginButton, 0, 2);
        loginButton.setDisable(true);

        Button registerButton = new Button("Register");
        gridpane.add(registerButton, 0, 2);

        registerButton.setOnAction(event -> {
            if (isEmpty(loginField.getText()) || isEmpty(passwordField.getText())) {
                AlertBox.display("Error!", "Input login and password");
            } else {
                if (user.Registrate(loginField.getText(), passwordField.getText())) {
                    label.setText("Login");
                    loginButton.setDisable(false);
                    registerButton.setVisible(false);
                    AlertBox.display("Success", "You are Registrated");
                    registratedUser = new RegistratedUser();
                    registratedUser.setAuthenticationService(user.getAuthenticationService());
                    registratedUser.setUserInformation(user.getAuthenticationService().getCurrentUserInformation());
                } else {
                    AlertBox.display("Error!", "You are not registrated");
                }
            }
        });

        loginButton.setOnAction(event -> {
            if (isEmpty(loginField.getText()) || isEmpty(passwordField.getText())) {
                AlertBox.display("Error!", "Input login and password");
            } else {
                if (registratedUser.getAuthenticationService()
                        .userExists(loginField.getText(), passwordField.getText())) {
                    primaryStage.setScene(isLoggedScene);
                } else {
                    AlertBox.display("Error", "User does not exist");
                }
            }
        });
//------------------------IS LOGGED SCENE---------------------------------------
        GridPane isLoggedLayout = new GridPane();
        isLoggedLayout.setPadding(new Insets(5));
        isLoggedLayout.setHgap(5);
        isLoggedLayout.setVgap(5);

        for (int i = 0; i < 2; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(100 / 3.0);
            isLoggedLayout.getColumnConstraints().add(column);
        }

        Label label1 = new Label("Hello");
        isLoggedLayout.add(label1, 0, 0);

        Button getInfoButton = new Button("get Info");
        isLoggedLayout.add(getInfoButton, 0, 1);

        Button changeCredentialsButton = new Button("Change Data");
        isLoggedLayout.add(changeCredentialsButton, 1, 1);

        getInfoButton.setOnAction(event -> registratedUser.getAuthenticationService().showUserInfo(registratedUser));

        changeCredentialsButton.setOnAction(event -> changeUserInfo());

        isLoggedScene = new Scene(isLoggedLayout, 330, 150);

//------------------------------------------------------------
        root.setCenter(gridpane);
        primaryStage.setScene(authenticationScene);
        primaryStage.show();

    }

    private boolean isEmpty(String text) {
        if (text.isEmpty()) return true;
        return false;
    }


    private void changeUserInfo() {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL); //block user interactions until this windows is handled
        window.setTitle("Change Information");
        window.setWidth(250);

        GridPane layout = new GridPane();
        layout.setPadding(new Insets(5));
        layout.setHgap(5);
        layout.setVgap(5);

        for (int i = 0; i < 4; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(100 / 3.0);
            layout.getColumnConstraints().add(column);
        }

        Label nameLabel = new Label("Your Name: ");
        Label loginLabel = new Label("Your login: ");
        Label nicknameLabel = new Label("Your nickName: ");
        Label emailLabel = new Label("Your email: ");

        layout.add(nameLabel, 0, 0);
        layout.add(loginLabel, 0, 1);
        layout.add(nicknameLabel, 0, 2);
        layout.add(emailLabel, 0, 3);

        TextField nameField = new TextField();
        layout.add(nameField, 1, 0);

        TextField loginField = new TextField();
        layout.add(loginField, 1, 1);

        TextField nicknameField = new TextField();
        layout.add(nicknameField, 1, 2);

        TextField emailField = new TextField();
        layout.add(emailField, 1, 3);

        Button submit = new Button("Submit");
        layout.add(submit, 0, 4);

        submit.setOnAction(event -> {

            if (isEmpty(nameField.getText()) || isEmpty(emailField.getText())
                    || isEmpty(nicknameField.getText()) || isEmpty(loginField.getText())) {
                AlertBox.display("error", "Input all data!");
            } else {
                registratedUser.getAuthenticationService().changeUserInformation(registratedUser, loginField.getText(),
                        nameField.getText(), nicknameField.getText(), emailField.getText());
                window.close();
            }
        });

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
