package App;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Logger;

import static App.Role.ADMIN;

public class Login {
    Logger logger = Logger.getLogger("Login");
    @FXML
    MenuBar myMenuBar2;
    @FXML
    TextField usernameLogin;
    @FXML
    PasswordField passwordLogin;
    @FXML
    Label communicates;
    @FXML
    Button _btn1;
    @FXML
    MenuItem aboutUs;

    public User user;

    public static String login, password, firstName, lastName, email;

    public void start(Stage stage) throws Exception {
        logger.info("start");
        Parent root = FXMLLoader.load(getClass().getResource("src/App/fxml/login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    @FXML
    private void handleKeyPressed(KeyEvent keyEvent)
    {
        logger.info("handleKeyPressed");
        if(keyEvent.getCode() == KeyCode.ENTER)
        {
           _btn1.fire();
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


    public void ewakuacja(ActionEvent actionEvent) {
        logger.info("handleKeyPressed");
        Platform.exit();
    }

    public void changeSceneToAboutUs(ActionEvent actionEvent) throws IOException {
        logger.info("handleKeyPressed");
        Parent root = FXMLLoader.load(getClass().getResource("aboutUs.fxml"));
        Scene Scene = new Scene(root);
        Stage window = (Stage) myMenuBar2.getScene().getWindow();
        window.setScene(Scene);
        window.show();
    }

    public void login (ActionEvent actionEvent) throws IOException {
        logger.info("handleKeyPressed");
        login = usernameLogin.getText();
        password = passwordLogin.getText();
        System.out.println(login + " " + password);
        Optional<User> user = Optional.empty();
        try {
            user = Connector.getInstance().getUserByLogin(login);
                firstName = user.get().firstName();
                lastName = user.get().lastName();
                email = user.get().email();
                user = Connector.getInstance().login(login, password);
        } catch (SQLException e) {
            logger.warning("handleKeyPressed" + e.getMessage());
            communicates.setText("niepoprawne dane logowania");
        }
        if(user.isPresent()) {
            if(user.get().role() == ADMIN)
            {
                URL url = new File("src/main/java/App/adminAccount.fxml").toURI().toURL();
                Parent root = FXMLLoader.load(url);
                Scene Scene = new Scene(root);
                Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                window.setScene(Scene);
                window.show();
            }
            else {
                Parent root = FXMLLoader.load(getClass().getResource("userAccount.fxml"));
                Scene Scene = new Scene(root);
                Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                window.setScene(Scene);
                window.show();
            }
        }
        else {
            communicates.setText("niepoprawne dane logowania");
        }
    }

    public void changeSceneToChooseYourAdventure(ActionEvent actionEvent) throws IOException {
        logger.info("changeSceneToChooseYourAdventure");
        Parent root = FXMLLoader.load(getClass().getResource("chooseYourAdventure.fxml"));
        Scene Scene = new Scene(root);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();
    }

}

