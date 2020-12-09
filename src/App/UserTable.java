package App;

import App.models.User;
import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ObservableStringValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class UserTable {

    @FXML
    private TableView<UserMaster> tableView;
    @FXML
    private TableColumn<UserMaster, String> firstNameColumn;
    @FXML
    private TableColumn<UserMaster, String> lastNameColumn;
    @FXML
    private  TableColumn<UserMaster, String> loginColumn;
    @FXML
    private TableColumn<UserMaster, Boolean> isActiveColumn;
    @FXML
    private TableColumn<UserMaster, Role> roleColumn;
    @FXML
    TextField filterField;
    @FXML
    private TableColumn<UserMaster, String> genderColumn;
    @FXML
    private TableColumn<UserMaster, Button> action;
    @FXML
    MenuBar myMenuBar4;
    @FXML
    MenuItem aboutUs;
    @FXML
    MenuItem logout;
    @FXML
    MenuItem back;
    @FXML
    Button button;


    public static ObservableList<UserMaster> userObservableList;
    public static FilteredList<UserMaster> userMasterFilteredList;
    public static SortedList<UserMaster> sortedList;
    public void initialize()
    {
        initTable();
        loadData();
    }

    public void initTable()
    {
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        loginColumn.setCellValueFactory(new PropertyValueFactory<>("login"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        isActiveColumn.setCellValueFactory(new PropertyValueFactory<>("isActive"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        action.setCellValueFactory(new PropertyValueFactory<>("delete"));
    }


    public ObservableList<UserMaster> loadData()
    {
    try {
        userObservableList = FXCollections.observableList(Connector.getInstance().showUsers());
        userMasterFilteredList = new FilteredList<>(userObservableList, b-> true);
        filterField.textProperty().addListener((observable, oldValue, newValue)->{
            userMasterFilteredList.setPredicate(userMaster -> {
                if(newValue == null || newValue.isEmpty())
                {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if(userMaster.getFirstName().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }
                else if(userMaster.getLastName().toLowerCase().contains(lowerCaseFilter))
                {
                    return true;
                }
                else if(userMaster.getLogin().toLowerCase().contains(lowerCaseFilter))
                {
                    return true;
                }
                else return false;
            });
        });
        sortedList = new SortedList<>(userMasterFilteredList);
        sortedList.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedList);
    }
    catch(SQLException e)
    {
        e.printStackTrace();
    }
        return userObservableList;
    }


    public void refreshTable() {
        tableView.refresh();
    }

    public void changeSceneToAboutUs(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/aboutUs.fxml"));
        Scene Scene = new Scene(root);
        Stage window = (Stage) myMenuBar4.getScene().getWindow();
        window.setScene(Scene);
        window.show();

    }
    public void changeSceneToChooseYourAdventure(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/chooseYourAdventure.fxml"));
        Scene Scene = new Scene(root);
        Stage window = (Stage) myMenuBar4.getScene().getWindow();
        window.setScene(Scene);
        window.show();

    }
    public void changeSceneToAdminAccount(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/adminAccount.fxml"));
        Scene Scene = new Scene(root);
        Stage window = (Stage) myMenuBar4.getScene().getWindow();
        window.setScene(Scene);
        window.show();

    }
    @FXML
    private void handleKeyPressed(KeyEvent keyEvent)
    {
        if(keyEvent.isControlDown()&&keyEvent.getCode() == KeyCode.L)
        {
            logout.fire();
        }
        if(keyEvent.isControlDown() && keyEvent.getCode() == KeyCode.F4)
        {
            System.out.println("wychodzę z użyciem ALT+F4");
            Platform.exit();
        }
        if ((keyEvent.isAltDown()||keyEvent.isControlDown()) && keyEvent.getCode() == KeyCode.A) {
            System.out.println("przyjmuje pozycje bojowa");
            aboutUs.fire();
        }
    }

}
