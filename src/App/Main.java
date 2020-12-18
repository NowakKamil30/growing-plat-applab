package App;

import App.models.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.logging.Logger;


public class Main extends Application {
    static Logger logger = Logger.getLogger("Main");

    private User user;

    @Override
    public void start(Stage primaryStage) throws Exception{
        logger.info("start");
        primaryStage.setTitle("Plant App");
//        Connector.getInstance().addUser(
//                new User(null, "login", "email", "password", "kamil", "nowak", "5",Role.ADMIN, true)
//        );
        Parent root;
        root = FXMLLoader.load(getClass().getResource("fxml/chooseYourAdventure.fxml"));
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        logger.info("main");
        System.setProperty("javafx.preloader", Splash.class.getName());
        Application.launch(Main.class, args);
    }

}