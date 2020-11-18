package App;

import java.util.Properties;
import java.sql.*;

public class Connector {

    private static Connector connector;
    private Statement statement;


    private Connector() {
        String url = "jdbc:mysql://localhost:3306/uz?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Properties info = new Properties();
        info.put("user", "dev");
        info.put("password", "password");

        try(Connection connection = DriverManager.getConnection(url,info)) {
            statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public void login(){

    }
    public static Connector getInstance(){
        if(connector == null) connector = new Connector();
        return connector;
    }



}
