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
import org.w3c.dom.Text;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

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

    public void registerAccount(ActionEvent actionEvent) throws IOException {

        this.password = registerPassword.getText();
        this.repeatPassword = registerRepeatPassword.getText();
        this.login = registerLogin.getText();
        this.email = registerEmail.getText();
        this.name = registerName.getText();
        this.surname = registerSurname.getText();
        Optional<User> user = Optional.empty();
            try {
                if (Connector.getInstance().getUserByLogin(login).isEmpty())
                    if (password.equals(repeatPassword)) {
                        Connector.getInstance().addUser(
                                new User(null, login, email, password, name, surname, Role.USER, true)
                        );
                        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
                        Scene Scene = new Scene(root);
                        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                        window.setScene(Scene);
                        window.show();
                    } else {
                        registerCommLabel.setText("cos poszlo nie tak");
                    }
            }
            catch(SQLException e){
            registerCommLabel.setText("Wystąpił błąd");
            e.printStackTrace();
        }
    }

    }

