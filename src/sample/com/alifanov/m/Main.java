package sample.com.alifanov.m;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setResizable(false);
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 330, 90);
        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(5));
        gridpane.setHgap(5);
        gridpane.setVgap(5);

        for (int i = 0; i < 2; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(100 / 3.0);
            gridpane.getColumnConstraints().add(column);
        }

        Label label = new Label("Login or Register");
        gridpane.add(label, 0, 0);

        TextField loginField = new TextField();
        loginField.setPromptText("login");
        gridpane.add(loginField, 0, 1);

        TextField passwordField = new TextField();
        passwordField.setPromptText("password");
        gridpane.add(passwordField, 1, 1);

        Button loginButton = new Button("Login");
        gridpane.add(loginButton, 0, 2);

        Button registerButton = new Button("Register");
        gridpane.add(registerButton, 1, 2);

        loginButton.setOnAction(e -> {
            if (isEmpty(loginField.getText()) || isEmpty(passwordField.getText())) {
                AlertBox.display("Error!", "Input login and password");
            }
        });

        registerButton.setOnAction(e -> {
            if (isEmpty(loginField.getText()) || isEmpty(passwordField.getText())) {
                AlertBox.display("Error!", "Input login and password");
            }
        });

        root.setCenter(gridpane);
        primaryStage.setScene(scene);
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
