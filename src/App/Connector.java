package App;

import App.models.User;

import javax.xml.transform.Result;
import java.util.*;
import java.sql.*;

public class Connector {

    private static Connector connector;
    private Connection connection;
    private Statement statement;


    private Connector() {
        String url = "jdbc:mysql://localhost:3306/uz?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Properties info = new Properties();
        info.put("user", "dev");
        info.put("password", "password");

        try{
            connection = DriverManager.getConnection(url,info);
            statement = connection.createStatement();
            createTableUser();
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

    private void createTableUser() throws SQLException {
        statement.execute("CREATE TABLE IF NOT EXISTS `uz`.`Users` (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `email` VARCHAR(45) NOT NULL,\n" +
                "  `login` VARCHAR(45) NOT NULL,\n" +
                "  `password` VARCHAR(45) NOT NULL,\n" +
                "  `first_name` VARCHAR(45) NOT NULL,\n" +
                "  `last_name` VARCHAR(45) NOT NULL,\n" +
                "  `role` ENUM(\"USER\", \"ADMIN\") NOT NULL,\n" +
                "  `isActive` TINYINT NOT NULL DEFAULT 0,\n" +
                "  PRIMARY KEY (`id`),\n" +
                "  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE)\n" +
                "ENGINE = InnoDB;");
    }

    public Optional<User> login(String login, String password) throws SQLException {
        ResultSet result = statement.executeQuery("SELECT * FROM Users WHERE login = " + login + " AND password = " + password);
        if(result.next()) {
            if (result.getBoolean("isActive")) {
                return Optional.of(new User(
                        result.getLong("id"),
                        result.getString("login"),
                        result.getString("email"),
                        result.getString("password"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        Role.valueOf(result.getString("role")),
                        result.getBoolean("isActive")));
            } else {
                return Optional.empty();
            }
        } else {
            return null;
        }
    }

    public void addUser(User user) throws SQLException {
        statement.execute(String.format("INSERT INTO Users (email, login, password, first_name, last_name, role, isActive) " +
                "VALUES ('%s', '%s', '%s', '%s', '%s', '%s', %b)", user.email(),
                user.login(),
                user.password(),
                user.firstName(),
                user.lastName(),
                user.role().toString(),
                true));
    }

    public void deleteUserById(Long id) throws SQLException {
        statement.execute("DELETE FROM Users WHERE id=" + id);
    }

    public void deleteUserByLogin(String login) throws SQLException {
        statement.execute("DELETE FROM Users WHERE login=" + login);
    }

    public Optional<User> getUserById(Long id) throws SQLException {
        ResultSet result = statement.executeQuery("SELECT * from Users WHERE id=" + id);
        if (result.next()) {
            return Optional.of(new User(
                    result.getLong("id"),
                    result.getString("login"),
                    result.getString("email"),
                    result.getString("password"),
                    result.getString("first_name"),
                    result.getString("last_name"),
                    Role.valueOf(result.getString("role")),
                    result.getBoolean("isActive")));
        }
        return Optional.empty();
    }

    public Optional<User> getUserByLogin(String login) throws SQLException {
        ResultSet result = statement.executeQuery("SELECT * from Users WHERE login=" + login);
        if (result.next()) {
            return Optional.of(new User(
                    result.getLong("id"),
                    result.getString("login"),
                    result.getString("email"),
                    result.getString("password"),
                    result.getString("first_name"),
                    result.getString("last_name"),
                    Role.valueOf(result.getString("role")),
                    result.getBoolean("isActive")));
        }
        return Optional.empty();
    }

    public List<User> showUsers() throws SQLException {
        ResultSet result = statement.executeQuery("SELECT * from Users");
        List<User> userList = new LinkedList<>();
        while (result.next()) {
            userList.add(
                    new User(
                            result.getLong("id"),
                            result.getString("login"),
                            result.getString("email"),
                            result.getString("password"),
                            result.getString("first_name"),
                            result.getString("last_name"),
                            Role.valueOf(result.getString("role")),
                            result.getBoolean("isActive"))
            );
        }
        return userList;
    }

    public void updateUser(Long id, User user) throws SQLException {
        statement.execute("UPDATE Users SET" +
                " email = " + user.email() +
                " , login = " + user.login() +
                " , password = " + user.password() +
                " , first_name =" + user.firstName() +
                " , last_name =" + user.lastName() +
                " , role =" + user.role().name() +
                " . isActive =" + user.isActive() +
                "WHERE id = " + id);
    }

    public void disconnect() throws SQLException {
        statement.close();
    }

}
