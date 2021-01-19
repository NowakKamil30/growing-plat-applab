package App;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;
import java.net.URL;

public class Main extends Application {
    //static Logger logger = Logger.getLogger("Main");

    private User user;

    @Override
    public void start(Stage primaryStage) throws Exception{
       // logger.info("start");
        primaryStage.setTitle("Plant App");
//        Connector.getInstance().addUser(
//                new User(null, "login", "email", "password", "kamil", "nowak", "5",Role.ADMIN, true)
//        );

        URL url = new File("src/main/java/App/chooseYourAdventure.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        //Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("chooseYourAdventure.fxml"));
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        //logger.info("main");
        //System.setProperty("javafx.preloader", Splash.class.getName());
        launch(args);
    }

}