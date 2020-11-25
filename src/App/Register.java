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
import java.sql.SQLException;

public class Register {
    @FXML
    MenuBar myMenuBar;
    public void ewakuacja(ActionEvent actionEvent) {
        Platform.exit();
    }
    public void changeSceneToChooseYourAdventure(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("chooseYourAdventure.fxml"));
        Scene Scene = new Scene(root);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();

    }
    public void changeSceneToAboutUs(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("aboutUs.fxml"));
        Scene Scene = new Scene(root);
        Stage window = (Stage) myMenuBar.getScene().getWindow();
        window.setScene(Scene);
        window.show();

    }
}
