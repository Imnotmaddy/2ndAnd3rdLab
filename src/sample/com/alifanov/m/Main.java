package sample.com.alifanov.m;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.com.alifanov.m.model.RegistratedUser;
import sample.com.alifanov.m.model.User;

public class Main extends Application {

    Scene authenticationScene, isLoggedScene;
    User user = new User();
    RegistratedUser registratedUser = new RegistratedUser();

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
        gridpane.add(label, 0,0);

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
            }
            else {
                if (user.Registrate(loginField.getText(), passwordField.getText())){
                    label.setText("Login");
                    loginButton.setDisable(false);
                    registerButton.setVisible(false);
                    AlertBox.display("Success", "You are Registrated");
                }
                else {
                    AlertBox.display("Error!", "You are not registrated");
                }
            }
        });

        loginButton.setOnAction(event-> {
            if (isEmpty(loginField.getText()) || isEmpty(passwordField.getText())) {
                AlertBox.display("Error!", "Input login and password");
            }
            else {
               if(registratedUser.getAuthenticationService()
                       .userExists(loginField.getText(), passwordField.getText())){
                   System.out.println("hello");
               }
                primaryStage.setScene(isLoggedScene);
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
        isLoggedLayout.add(getInfoButton, 0,1);

        Button changeCredentialsButton = new Button("Change Data");
        isLoggedLayout.add(changeCredentialsButton,1,1);

        isLoggedScene = new Scene(isLoggedLayout,330, 150);

//------------------------------------------------------------
        root.setCenter(gridpane);
        primaryStage.setScene(authenticationScene);
        primaryStage.show();

    }

    private boolean isEmpty(String text) {
        if (text.isEmpty()) return true;
        return false;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
