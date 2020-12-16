package App;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.logging.Logger;

public class UserAccount {
    Logger logger = Logger.getLogger("UserAccount");
    private String something = "uzytkownika";

    @FXML
    MenuBar myMenuBar4;
    @FXML
    Label userAccountName;
    @FXML
    Label userAccountEmail;
    @FXML
    Label workName;
    @FXML
    MenuItem aboutUs;
    @FXML
    Button logout;

    public static String login = Login.login;
    public static String firstName = Login.firstName;
    public static String lastName = Login.lastName;
    public static String email = Login.email;



    public void initialize()
    {
        logger.info("initialize");
        this.userAccountName.setText(firstName+" "+lastName);
        this.userAccountEmail.setText(email);
        this.workName.setText(something);
    }

    public void changeSceneToAboutUs(ActionEvent actionEvent) throws IOException {
        logger.info("changeSceneToAboutUs");
        Parent root = FXMLLoader.load(getClass().getResource("fxml/aboutUs.fxml"));
        Scene Scene = new Scene(root);
        Stage window = (Stage) myMenuBar4.getScene().getWindow();
        window.setScene(Scene);
        window.show();

    }
    public void changeSceneToChooseYourAdventure(ActionEvent actionEvent) throws IOException {
        logger.info("changeSceneToChooseYourAdventure");
        Parent root = FXMLLoader.load(getClass().getResource("fxml/chooseYourAdventure.fxml"));
        Scene Scene = new Scene(root);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();

    }
    public void ewakuacja(ActionEvent actionEvent) {
        logger.info("ewakuacja");
        Platform.exit();
    }

    @FXML
    private void handleKeyPressed(KeyEvent keyEvent)
    {
        logger.info("handleKeyPressed");
        if(keyEvent.isControlDown()&&keyEvent.getCode() == KeyCode.L)
        {
            logout.fire();
        }
        if(keyEvent.isControlDown() && keyEvent.getCode() == KeyCode.F4)
        {
            System.out.println("wychodzę z użyciem ALT+F4");
            Platform.exit();
        }
        if ((keyEvent.isAltDown()||keyEvent.isControlDown()) && keyEvent.getCode() == KeyCode.A) {
            System.out.println("przyjmuje pozycje bojowa");
            aboutUs.fire();
        }
    }
}
