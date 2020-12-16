package App;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;

public class ReadSettings {
    static Logger logger = Logger.getLogger("ReadSettings");
    private String connectedString;
    private String username;
    private String password;
    private static ReadSettings readJsonSettings;

    private ReadSettings() {
        logger.info("ReadSettings");
        try (BufferedReader reader = new BufferedReader(new FileReader("settings.csv"))) {
            String row;
            int index = 0;
            while ((row = reader.readLine()) != null) {
                String[] data = row.split(",");
                if(index == 1) {
                    connectedString = data[0];
                    username = data[1];
                    password = data[2];
                }
                index ++;
            }
        } catch (IOException e) {
            logger.warning("ReadSettings" + e.getMessage());
        }
    }
        public static ReadSettings getInstance() {
            logger.info("getInstance");
        if (readJsonSettings == null) {
            readJsonSettings = new ReadSettings();
        }
        return readJsonSettings;
    }

    public String getConnectedString() {
        logger.info("getConnectedString");
        return connectedString;
    }

    public void setConnectedString(String connectedString) {
        logger.info("setConnectedString");
        this.connectedString = connectedString;
    }

    public String getUsername() {
        logger.info("getUsername");
        return username;
    }

    public void setUsername(String username) {
        logger.info("setUsername");
        this.username = username;
    }

    public String getPassword() {
        logger.info("getPassword");
        return password;
    }

    public void setPassword(String password) {
        logger.info("setPassword");
        this.password = password;
    }
}
