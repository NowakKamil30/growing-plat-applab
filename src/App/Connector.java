package App;

import App.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.transform.Result;
import java.util.*;
import java.sql.*;
import java.util.logging.Logger;

public class Connector {
    static Logger logger = Logger.getLogger("Connector");

    private static Connector connector;
    private Connection connection;
    private Statement statement;


    private Connector() {
        logger.info("Connector");
        ReadSettings readSettings = ReadSettings.getInstance();
        String url = readSettings.getConnectedString();
        Properties info = new Properties();
        info.put("user", readSettings.getUsername());
        info.put("password", readSettings.getPassword());

        try{
            connection = DriverManager.getConnection(url,info);
            statement = connection.createStatement();
            createTableUser();
        } catch (SQLException throwables) {
            logger.warning("Connector "+ throwables.getMessage());
        }

    }
    public void login(){

    }

    public static Connector getInstance(){
        logger.info("getInstance");
        if(connector == null) connector = new Connector();
        return connector;
    }

    private void createTableUser() throws SQLException {
        logger.info("createTableUser");
        statement.execute("CREATE TABLE IF NOT EXISTS `uz`.`Users` (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `email` VARCHAR(45) NOT NULL,\n" +
                "  `login` VARCHAR(45) NOT NULL,\n" +
                "  `password` VARCHAR(45) NOT NULL,\n" +
                "  `first_name` VARCHAR(45) NOT NULL,\n" +
                "  `last_name` VARCHAR(45) NOT NULL,\n" +
                "  `gender` VARCHAR(45) NOT NULL,\n" +
                "  `role` ENUM(\"USER\", \"ADMIN\") NOT NULL,\n" +
                "  `isActive` TINYINT NOT NULL DEFAULT 0,\n" +
                "  PRIMARY KEY (`id`),\n" +
                "  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE)\n" +
                "ENGINE = InnoDB;");
    }

    public Optional<User> login(String login, String password) throws SQLException {
        logger.info("login");
        ResultSet result = statement.executeQuery("SELECT * FROM Users WHERE login = " + "'" + login + "'" + " AND password = " + "'"+password+"'");
        if(result.next()) {
            if (result.getBoolean("isActive")) {
                return Optional.of(new User(
                        result.getLong("id"),
                        result.getString("login"),
                        result.getString("email"),
                        result.getString("password"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("gender"),
                        Role.valueOf(result.getString("role")),
                        result.getBoolean("isActive")));
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    public void addUser(User user) throws SQLException {
        logger.info("addUser");
        statement.execute(String.format("INSERT INTO Users (email, login, password, first_name, last_name, gender, role, isActive) " +
                "VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', %b)",
                user.email(),
                user.login(),
                user.password(),
                user.firstName(),
                user.lastName(),
                user.gender(),
                user.role().toString(),
                true));
    }

    public void deleteUserById(Long id) throws SQLException {
        logger.info("deleteUserById");
        statement.execute("DELETE FROM Users WHERE id=" + id);
    }

    public void deleteUserByLogin(String login) throws SQLException {
        logger.info("deleteUserByLogin");
        statement.execute("DELETE FROM Users WHERE login=" + "'" + login + "'");
    }

    public Optional<User> getUserById(Long id) throws SQLException {
        logger.info("getUserById");
        ResultSet result = statement.executeQuery("SELECT * from Users WHERE id=" + id);
        if (result.next()) {
            return Optional.of(new User(
                    result.getLong("id"),
                    result.getString("login"),
                    result.getString("email"),
                    result.getString("password"),
                    result.getString("first_name"),
                    result.getString("last_name"),
                    result.getString("gender"),
                    Role.valueOf(result.getString("role")),
                    result.getBoolean("isActive")));
        }
        return Optional.empty();
    }

    public Optional<User> getUserByLogin(String login) throws SQLException {
        logger.info("getUserByLogin");
        ResultSet result = statement.executeQuery("SELECT * from `Users` WHERE `login`=" + "'" + login + "'");
        if (result.next()) {
            return Optional.of(new User(
                    result.getLong("id"),
                    result.getString("login"),
                    result.getString("email"),
                    result.getString("password"),
                    result.getString("first_name"),
                    result.getString("last_name"),
                    result.getString("gender"),
                    Role.valueOf(result.getString("role")),
                    result.getBoolean("isActive")));
        }
        return Optional.empty();
    }

    public ObservableList<UserMaster> showUsers() throws SQLException {
        logger.info("showUsers");

        ObservableList<UserMaster> data;
        data = FXCollections.observableArrayList();

        ResultSet result = statement.executeQuery("SELECT * from Users");
        while (result.next()) {
                            UserMaster userMaster = new UserMaster();
                            userMaster.id.set(result.getLong("id"));
                            userMaster.login.set(result.getString("login"));
                            userMaster.email.set(result.getString("email"));
                            userMaster.password.set(result.getString("password"));
                            userMaster.firstName.set(result.getString("first_name"));
                            userMaster.lastName.set(result.getString("last_name"));
                            userMaster.role.set(result.getString("role"));
                            userMaster.isActive.set(result.getBoolean("isActive"));
                            userMaster.gender.set(result.getString("gender"));
                            data.add(userMaster);
        }
        return data;
    }

    public List<User> filterByFirstLetter(String letter) throws SQLException {
        logger.info("filterByFirstLetter");
        ResultSet result = statement.executeQuery("SELECT * from Users WHERE first_name LIKE '" + letter + "%'");
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
                            result.getString("gender"),
                            Role.valueOf(result.getString("role")),
                            result.getBoolean("isActive"))
            );
        }
        return userList;
    }

    public void updateUser(Long id, User user) throws SQLException {
        logger.info("updateUser");
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
        logger.info("disconnect");
        statement.close();
    }

}
