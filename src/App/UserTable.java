package App;


import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

public class UserTable {
    Logger logger = Logger.getLogger("UserTable");

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
    MenuBar myMenuBar4;
    @FXML
    MenuItem aboutUs;
    @FXML
    MenuItem logout;
    @FXML
    MenuItem back;
    @FXML
    Label infLabel;

    ContextMenu contextMenu = new ContextMenu();
    MenuItem menuItem = new MenuItem("usuń");
    MenuItem menuItem2 = new MenuItem("odśwież");

    public static ObservableList<UserMaster> userObservableList;
    public static FilteredList<UserMaster> userMasterFilteredList;
    public static SortedList<UserMaster> sortedList;


    public void initialize()
    {
        logger.info("initialize");
        initTable();
        loadData();
    }

    public void initTable()
    {
        logger.info("initTable");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        loginColumn.setCellValueFactory(new PropertyValueFactory<>("login"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        isActiveColumn.setCellValueFactory(new PropertyValueFactory<>("isActive"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        contextMenu.getItems().addAll(menuItem, menuItem2);
        tableView.setContextMenu(contextMenu);
    }

    public ObservableList<UserMaster> loadData()
    {
        logger.info("loadData");
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
        logger.warning("loadData" + e.getMessage());
    }
        addButton();
        return userObservableList;
    }


    public void addButton()
    {
        logger.info("addButton");
        TableColumn<UserMaster, Void> colBtn = new TableColumn("");
        Callback<TableColumn<UserMaster, Void>, TableCell<UserMaster, Void>> cellFactory = new Callback<TableColumn<UserMaster, Void>, TableCell<UserMaster, Void>>() {
            @Override
            public TableCell<UserMaster, Void> call(final TableColumn<UserMaster, Void> param) {
                final TableCell<UserMaster, Void> cell = new TableCell<UserMaster, Void>() {
                    private final Button btn = new Button("usuń");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            UserMaster userMaster = getTableView().getItems().get(getIndex());
                            try {
                                Connector.getInstance().deleteUserByLogin(userMaster.getLogin());
                                userObservableList.remove(userMaster);
                            }catch(SQLException e)
                            {
                                e.printStackTrace();
                            }

                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        logger.info("updateItem");
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        colBtn.setCellFactory(cellFactory);
        tableView.getColumns().add(colBtn);
        menuItem2.setOnAction(actionEvent -> {
            loadData();
        });
        addBtn();
    }

    public void addBtn()
    {
        logger.info("addBtn");
        tableView.setRowFactory(tv ->{
            TableRow<UserMaster> row = new TableRow<>();
            row.setOnMouseClicked(mouseEvent -> {
                if(! row.isEmpty() && mouseEvent.getButton()== MouseButton.SECONDARY)
                {
                    UserMaster userMaster = row.getItem();
                    menuItem.setOnAction(actionEvent -> {
                        userObservableList.remove(userMaster);
                        try{
                            Connector.getInstance().deleteUserByLogin(userMaster.getLogin());
                            infLabel.setText("usunąłeś użytkownika");
                        }catch(SQLException e)
                        {
                            infLabel.setText("Wystąpił błąd, nie udało się usunąć użytkownika");
                            e.printStackTrace();
                        }
                    });
                }
            });
            return row;
        });
    }

    public void refreshTable() {

        logger.info("refreshTable");
        tableView.refresh();
    }

    public void changeSceneToAboutUs(ActionEvent actionEvent) throws IOException {
        logger.info("changeSceneToAboutUs");
        Parent root = FXMLLoader.load(getClass().getResource("fxml/aboutUs.fxml"));
        Scene Scene = new Scene(root);
        Stage window = (Stage) myMenuBar4.getScene().getWindow();
        window.setScene(Scene);
        window.show();

    }
    public void changeSceneToChooseYourAdventure(ActionEvent actionEvent) throws IOException {
        logger.info("changeSceneToChooseYourAdventure");
        Parent root = FXMLLoader.load(getClass().getResource("fxml/chooseYourAdventure.fxml"));
        Scene Scene = new Scene(root);
        Stage window = (Stage) myMenuBar4.getScene().getWindow();
        window.setScene(Scene);
        window.show();

    }
    public void changeSceneToAdminAccount(ActionEvent actionEvent) throws IOException {
        logger.info("changeSceneToAdminAccount");
        Parent root = FXMLLoader.load(getClass().getResource("fxml/adminAccount.fxml"));
        Scene Scene = new Scene(root);
        Stage window = (Stage) myMenuBar4.getScene().getWindow();
        window.setScene(Scene);
        window.show();

    }
    @FXML
    private void handleKeyPressed(KeyEvent keyEvent)
    {
        logger.info("handleKeyPressed");
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
