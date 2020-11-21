package App;

import App.models.User;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class Login {

    @FXML
    MenuBar myMenuBar2;
    @FXML
    TextField usernameLogin;
    @FXML
    PasswordField passwordLogin;
    @FXML
    Label communicates;

    public static String login, password;

    public void ewakuacja(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void changeSceneToAboutUs(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("aboutUs.fxml"));
        Scene Scene = new Scene(root);
        Stage window = (Stage) myMenuBar2.getScene().getWindow();
        window.setScene(Scene);
        window.show();
    }

    public void login (ActionEvent actionEvent) throws IOException {
        this.login = usernameLogin.getText();
        this.password = passwordLogin.getText();
        System.out.println(login + " " + password);
        Optional<User> user = Optional.empty();
        try {
            user = Connector.getInstance().login(login, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            communicates.setText("niepoprawne dane logowania");
        }
        if(user.isPresent()) {
            Parent root = FXMLLoader.load(getClass().getResource("userAccount.fxml"));
            Scene Scene = new Scene(root);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(Scene);
            window.show();
        }
        else {
            communicates.setText("niepoprawne dane logowania");
        }
    }

    public void changeSceneToChooseYourAdventure(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("chooseYourAdventure.fxml"));
        Scene Scene = new Scene(root);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();

    }
    /*
        Parent root = FXMLLoader.load(getClass().getResource("userAccount.fxml"));
        Scene Scene = new Scene(root);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();
     */

}
