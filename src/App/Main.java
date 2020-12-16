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
//        Connector.getInstance().addUser(
//                new User(null, "login", "email", "password", "kamil", "nowak", "5",Role.ADMIN, true)
//        );
        Parent root;
      //  Parent root2;
      //  root2 = FXMLLoader.load(getClass().getResource("fxml/splash.fxml"));
        root = FXMLLoader.load(getClass().getResource("fxml/chooseYourAdventure.fxml"));
     //   primaryStage.setScene(new Scene(root2, 300, 275));
     //   primaryStage.show();

        primaryStage.setTitle("Plant App");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

    }


    public static void main(String[] args) {
        logger.info("main");
        launch(args);
    }

private void loadSplashScreen()
{}
}