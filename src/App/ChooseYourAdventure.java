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
import java.util.logging.Logger;

public class ChooseYourAdventure {
    Logger logger = Logger.getLogger("ChooseYourAdventure");
    @FXML
    MenuBar myMenuBar3;
    public void ewakuacja(ActionEvent actionEvent) {
        logger.info("ewakuacja");
        Platform.exit();
    }

    public void changeSceneToLogin(ActionEvent actionEvent) throws IOException {
        logger.info("changeSceneToLogin");
        Parent root = FXMLLoader.load(getClass().getResource("fxml/login.fxml"));
        Scene Scene = new Scene(root);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();

    }
    public void changeSceneToRegister(ActionEvent actionEvent) throws IOException {
        logger.info("changeSceneToRegister");
        Parent root = FXMLLoader.load(getClass().getResource("fxml/register.fxml"));
        Scene Scene = new Scene(root);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();

    }
    public void changeSceneToAboutUs(ActionEvent actionEvent) throws IOException {
        logger.info("changeSceneToRegister");
        Parent root = FXMLLoader.load(getClass().getResource("fxml/aboutUs.fxml"));
        Scene Scene = new Scene(root);
        Stage window = (Stage) myMenuBar3.getScene().getWindow();
        window.setScene(Scene);
        window.show();

    }

}
