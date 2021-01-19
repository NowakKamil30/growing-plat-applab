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
import java.sql.SQLException;
import java.util.logging.Logger;

public class Register {
    Logger logger = Logger.getLogger("Register");
    @FXML
    MenuBar myMenuBar;
    @FXML
    TextField registerName;
    @FXML
    TextField registerSurname;
    @FXML
    TextField registerEmail;
    @FXML
    TextField registerLogin;
    @FXML
    PasswordField registerPassword;
    @FXML
    PasswordField registerRepeatPassword;
    @FXML
    Label registerCommLabel;
    @FXML
    MenuItem aboutUs;
    @FXML
    Button register;
    @FXML
    ComboBox comboBox;

    String gender = "Mężczyzna";

    public void initialize()
    {
        logger.info("initialize");
        comboBox.getItems().removeAll(comboBox.getItems());
        comboBox.getItems().addAll("Mężczyzna", "Kobieta", "Nieokreślona");
        comboBox.getSelectionModel().select("Mężczyzna");
    }

    public void ewakuacja(ActionEvent actionEvent) {
        logger.info("ewakuacja");
        Platform.exit();
    }

    public void changeSceneToChooseYourAdventure(ActionEvent actionEvent) throws IOException {
        logger.info("changeSceneToChooseYourAdventure");
        Parent root = FXMLLoader.load(getClass().getResource("chooseYourAdventure.fxml"));
        Scene Scene = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();

    }

    public void changeSceneToAboutUs(ActionEvent actionEvent) throws IOException {
        logger.info("changeSceneToAboutUs");
        Parent root = FXMLLoader.load(getClass().getResource("aboutUs.fxml"));
        Scene Scene = new Scene(root);
        Stage window = (Stage) myMenuBar.getScene().getWindow();
        window.setScene(Scene);
        window.show();
    }

    public void changeSceneToLogin(ActionEvent actionEvent) throws IOException {
        logger.info("changeSceneToLogin");
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene Scene = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();

    }

    public void registerAccount(ActionEvent actionEvent) throws IOException {
        logger.info("registerAccount");
        String password = registerPassword.getText();
        String repeatPassword = registerRepeatPassword.getText();
        String login = registerLogin.getText();
        String email = registerEmail.getText();
        String name = registerName.getText();
        String surname = registerSurname.getText();
        gender = comboBox.getSelectionModel().getSelectedItem().toString();
        try {
            if (Connector.getInstance().getUserByLogin(login).isEmpty() && !login.isEmpty()) {
                if (password.equals(repeatPassword) && !password.isEmpty()) {
                    Connector.getInstance().addUser(
                            new User(null, login, email, password, name, surname, gender, Role.USER, true)
                    );

                    changeSceneToLogin(actionEvent);

                } else {
                    registerCommLabel.setText("cos poszlo nie tak");
                }
            } else {
                registerCommLabel.setText("Wystąpił błąd");
            }
        } catch (SQLException e) {
            registerCommLabel.setText("Wystąpił błąd");
            e.printStackTrace();
        }
    }
    @FXML
    private void handleKeyPressed(KeyEvent keyEvent)
    {
        logger.info("handleKeyPressed");
        if(keyEvent.getCode() == KeyCode.ENTER)
        {
            register.fire();
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

