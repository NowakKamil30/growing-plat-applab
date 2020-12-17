package App;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Simple Preloader Using the ProgressBar Control
 *
 * @author Łukasz
 */
public class JavaFxApplication6_Preloader extends Preloader {

    ProgressBar bar;
    Stage stage;

    private Scene createPreloaderScene() {
        StackPane root = new StackPane();
        ImageView img = new ImageView("splash.jpg");
        root.getChildren().add(img);
        bar = new ProgressBar();
        BorderPane p = new BorderPane();
        p.setCenter(bar);
        return new Scene(root, 732, 548);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(createPreloaderScene());
        stage.show();

    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification scn) {
        if (scn.getType() == StateChangeNotification.Type.BEFORE_START) {
            stage.hide();
        }
    }

    @Override
    public void handleProgressNotification(ProgressNotification pn) {

        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        bar.setProgress(pn.getProgress());
    }

}