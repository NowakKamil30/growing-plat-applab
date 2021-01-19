package App;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;

public class AdminAccount {
    Logger logger = Logger.getLogger("AdminAccount");

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
    @FXML
    Button userTableButton;
    @FXML
    MenuItem userTable;

    public static String something = "administratora";
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
        Parent root = FXMLLoader.load(getClass().getResource("aboutUs.fxml"));
        Scene Scene = new Scene(root);
        Stage window = (Stage) myMenuBar4.getScene().getWindow();
        window.setScene(Scene);
        window.show();

    }
    public void changeSceneToUserTable(ActionEvent actionEvent) throws IOException {
        logger.info("changeSceneToUserTable");
        URL url = new File("src/main/java/App/userTable.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene Scene = new Scene(root);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();

    }
    public void changeSceneToUserTableFromMenu(ActionEvent actionEvent) throws IOException {
        logger.info("userTableFromMenu");
        Parent root = FXMLLoader.load(getClass().getResource("userTable.fxml"));
        Scene Scene = new Scene(root);
        Stage window = (Stage) myMenuBar4.getScene().getWindow();
        window.setScene(Scene);
        window.show();

    }
    public void changeSceneToChooseYourAdventure(ActionEvent actionEvent) throws IOException {
        logger.info("changeSceneToChooseYourAdventure");
        Parent root = FXMLLoader.load(getClass().getResource("chooseYourAdventure.fxml"));
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
        if (keyEvent.isControlDown()&&keyEvent.getCode() == KeyCode.U)
        {
            userTable.fire();
        }
    }
}
