package App;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;
import java.io.IOException;

public class UserAccount {

    private String name = "noname";
    private String surname = "nosurname";
    private String email = "nomail";
    private String something = "something will be here";

    @FXML
    MenuBar myMenuBar4;
    @FXML
    Label userAccountName;
    @FXML
    Label userAccountEmail;
    @FXML
    Label workName;



    public void initialize()
    {
        this.userAccountName.setText(name+" "+surname);
        this.userAccountEmail.setText(email);
        this.workName.setText(something);
    }

    public void changeSceneToAboutUs(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("aboutUs.fxml"));
        Scene Scene = new Scene(root);
        Stage window = (Stage) myMenuBar4.getScene().getWindow();
        window.setScene(Scene);
        window.show();

    }
    public void changeSceneToChooseYourAdventure(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("chooseYourAdventure.fxml"));
        Scene Scene = new Scene(root);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();

    }
    public void ewakuacja(ActionEvent actionEvent) {
        Platform.exit();
    }
}
