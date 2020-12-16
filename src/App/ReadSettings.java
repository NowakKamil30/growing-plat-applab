package App;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class ReadSettings {
    private String connectedString;
    private String username;
    private String password;
    private static ReadSettings readJsonSettings;

    private ReadSettings() {
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
            e.printStackTrace();
        }
    }
        public static ReadSettings getInstance() {
        if (readJsonSettings == null) {
            readJsonSettings = new ReadSettings();
        }
        return readJsonSettings;
    }

    public String getConnectedString() {
        return connectedString;
    }

    public void setConnectedString(String connectedString) {
        this.connectedString = connectedString;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
