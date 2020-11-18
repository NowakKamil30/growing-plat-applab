package App;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

import java.io.IOException;

public class ChooseYourAdventure {
    @FXML
    MenuBar myMenuBar3;
    public void ewakuacja(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void changeSceneToLogin(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene Scene = new Scene(root);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();

    }
    public void changeSceneToRegister(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
        Scene Scene = new Scene(root);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();

    }
    public void changeSceneToAboutUs(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("aboutUs.fxml"));
        Scene Scene = new Scene(root);
        Stage window = (Stage) myMenuBar3.getScene().getWindow();
        window.setScene(Scene);
        window.show();

    }

}
