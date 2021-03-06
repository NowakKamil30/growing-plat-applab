package App;

import App.models.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        /*
        Connector.getInstance().addUser(
                new User(null, "login", "email", "password", "kamil", "nowak", Role.USER, true)
        );
        */
        Parent root;
        root = FXMLLoader.load(getClass().getResource("chooseYourAdventure.fxml"));
        primaryStage.setTitle("Plant App");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
