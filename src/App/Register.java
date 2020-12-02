package App;

import App.models.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Register {
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

    private String name;
    private String surname;
    private String email;
    private String login;
    private String password;
    private String repeatPassword;

    public void ewakuacja(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void changeSceneToChooseYourAdventure(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/chooseYourAdventure.fxml"));
        Scene Scene = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();

    }

    public void changeSceneToAboutUs(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/aboutUs.fxml"));
        Scene Scene = new Scene(root);
        Stage window = (Stage) myMenuBar.getScene().getWindow();
        window.setScene(Scene);
        window.show();
    }

    public void changeSceneToLogin(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/login.fxml"));
        Scene Scene = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();

    }

    public void registerAccount(ActionEvent actionEvent) throws IOException {

        this.password = registerPassword.getText();
        this.repeatPassword = registerRepeatPassword.getText();
        this.login = registerLogin.getText();
        this.email = registerEmail.getText();
        this.name = registerName.getText();
        this.surname = registerSurname.getText();
        try {
            if (Connector.getInstance().getUserByLogin(login).isEmpty() && !login.isEmpty()) {
                if (password.equals(repeatPassword) && !password.isEmpty()) {
                    Connector.getInstance().addUser(
                            new User(null, login, email, password, name, surname, Role.USER, true)
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
}

