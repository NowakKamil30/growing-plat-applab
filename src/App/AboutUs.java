package App;

import javafx.application.Platform;
import javafx.event.ActionEvent;

import java.util.logging.Logger;

public class AboutUs {
    Logger logger = Logger.getLogger("AboutUs");
    public void ewakuacja(ActionEvent actionEvent) {
        logger.info("AboutUs");
        Platform.exit();
    }
}
