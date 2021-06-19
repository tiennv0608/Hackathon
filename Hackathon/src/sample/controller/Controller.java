package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.model.Event;
import sample.model.UserAccount;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Label userName;
    @FXML
    private Label fullName;
    @FXML
    private Label age;
    @FXML
    private Label gender;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private TableView<Event> tableView;
    @FXML
    private TableColumn<Event, String> colUserName;
    @FXML
    private TableColumn<Event, String> colName;
    @FXML
    private TableColumn<Event, String> colType;
    @FXML
    private TableColumn<Event, String> colTime;
    @FXML
    private TableColumn<Event, String> place;
    @FXML
    private TableColumn<Event, String> status;
    @FXML
    private TableColumn<Event, String> note;
    private ObservableList<Event> events;
    @FXML
    private TextField idEvent;
    @FXML
    private ComboBox<String> idType;
    @FXML
    private TextField idTime;
    @FXML
    private TextField idPlace;
    @FXML
    private TextField idMaxPerson;
    @FXML
    private TextArea idDes;

    public void logIn(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("dangnhap.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
    }

    public void setAccount(UserAccount userAccount) {
        userName.setText(userAccount.getUserName());
        fullName.setText(userAccount.getFullname());
        age.setText(String.valueOf(userAccount.getAge()));
        gender.setText(userAccount.getGender());
        phone.setText(userAccount.getPhoneNumber());
        address.setText(userAccount.getAddress());
    }

    public UserAccount getAccount() {
        UserAccount account = new UserAccount();
        account.setUserName(userName.getText());
        account.setFullname(fullName.getText());
        account.setAge(Integer.parseInt(age.getText()));
        account.setGender(gender.getText());
        account.setAddress(address.getText());
        account.setPhoneNumber(phone.getText());
        return account;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        events = FXCollections.observableArrayList();
        colUserName.setCellValueFactory(new PropertyValueFactory<Event, String>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<Event, String>("name"));
        colType.setCellValueFactory(new PropertyValueFactory<Event, String>("sport"));
        colTime.setCellValueFactory(new PropertyValueFactory<Event, String>("time"));
        place.setCellValueFactory(new PropertyValueFactory<Event, String>("place"));
        status.setCellValueFactory(new PropertyValueFactory<Event, String>("status"));
        note.setCellValueFactory(new PropertyValueFactory<Event, String>("des"));
        tableView.setItems(events);
    }

    public Event create() {
        Event event = new Event();
        event.setId(userName.getText());
        event.setName(idEvent.getText());
        event.setSport(String.valueOf(idType.getValue()));
        event.setTime(idTime.getText());
        event.setPlace(idPlace.getText());
        event.setMaxPerson(Integer.parseInt(idMaxPerson.getText()));
        event.setDes(idDes.getText());
        event.getQuantity().add(getAccount());
        return event;
    }

    public void add(ActionEvent e) {
        confirm();
        tableView.refresh();
        refreshForm();
    }

    public void refreshForm() {
        idEvent.setText("");
        idType.setValue("");
        idTime.setText("");
        idPlace.setText("");
        idMaxPerson.setText("");
        idDes.setText("");
    }

    public void confirm() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận thao tác");
        alert.setHeaderText(null);
        alert.setContentText("Bạn có chắc chắn muốn thực hiện thao tác này?");

        ButtonType buttonYes = new ButtonType("Đồng ý");
        ButtonType buttonCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonYes, buttonCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonYes) {
            events.add(create());
            refreshForm();
        } else {
            alert.close();
        }
    }
}

