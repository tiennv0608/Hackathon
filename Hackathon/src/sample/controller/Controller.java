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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.model.Event;
import sample.model.UserAccount;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
    private Event eventSelected;
    @FXML
    private DialogPane notification;

    public void logOut(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/login.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
    }

    public ObservableList<Event> getAll() {
        return events;
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

    public void showAll(ActionEvent e) {
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
        event.setQuantity();
        return event;
    }

    public void addEvent(ActionEvent e) {
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

    public void search(ActionEvent e) {
        ObservableList<Event> searchList = FXCollections.observableArrayList();
        for (Event event : events) {
            if (event.getPlace().equals(idPlace.getText()) || event.getName().equals(idEvent.getText()) ||
                    event.getSport().equals(idType.getValue()) || event.getTime().equals(idTime.getText())) {
                searchList.add(event);
            }
        }
        tableView.setItems(searchList);
    }

    public Event getSelectedItem(MouseEvent click) {
        if (click.getClickCount() == 2) {
            eventSelected = tableView.getSelectionModel().getSelectedItem();
            if (eventSelected.getId().equals(userName.getText())) {
                confirmUserAction(eventSelected);
            } else {
                notification.setContentText("Bạn không thể sửa hay xóa sự kiện này!");
            }
        }
        return eventSelected;
    }

    public void confirmUserAction(Event selectedEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận thao tác");
        alert.setHeaderText(null);
        alert.setContentText("Lựa chọn thao tác bạn muốn thực hiện");
        ButtonType buttonEdit = new ButtonType("Chỉnh sửa");
        ButtonType buttonDelete = new ButtonType("Xóa");
        ButtonType buttonCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonEdit, buttonDelete, buttonCancel);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonEdit) {
            editActionSelected(selectedEvent);
        } else if (result.get() == buttonDelete) {
            confirmDeleteDialog(selectedEvent);
        } else {
            alert.close();
        }
    }

    public void editActionSelected(Event event) {
        idEvent.setText(event.getName());
        idTime.setText(event.getTime());
        idType.setValue(event.getSport());
        idPlace.setText(event.getPlace());
        idMaxPerson.setText(String.valueOf(event.getMaxPerson()));
        idDes.setText(event.getDes());
    }

    public void confirmDeleteDialog(Event event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận thao tác");
        alert.setHeaderText(null);
        alert.setContentText("Bạn có chắc chắn muốn xóa sự kiện này?");

        ButtonType buttonYes = new ButtonType("Xóa");
        ButtonType buttonCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonYes, buttonCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonYes) {
            deleteActionSelected(event);
        } else {
            alert.close();
        }
    }

    public void deleteActionSelected(Event event) {
        events.remove(event);
    }

    public void update(ActionEvent event) {
        eventSelected.setName(idEvent.getText());
        eventSelected.setSport(idType.getValue());
        eventSelected.setTime(idTime.getText());
        eventSelected.setPlace(idPlace.getText());
        eventSelected.setMaxPerson(Integer.parseInt(idMaxPerson.getText()));
        eventSelected.setDes(idDes.getText());
        tableView.refresh();
        refreshForm();
    }

    public void importListEvent(ActionEvent event) {
        List<Event> list = IOEvent.readFromFile(IOEvent.PATH);
        events.setAll(list);
    }

    public void exportListEvent(ActionEvent event) {
        IOEvent.writeToFile(IOEvent.PATH, events);
    }

    public boolean checkExistedUser(String id) {
        for (Event event : getAll()) {
            for (UserAccount userAccount : event.getQuantity()) {
                if (userAccount.getUserName().equals(id)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void registerEvent(ActionEvent event) {
        eventSelected = tableView.getSelectionModel().getSelectedItem();
        if (!eventSelected.getId().equals(userName.getText())) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Xác nhận thao tác");
            alert.setHeaderText(null);
            alert.setContentText("Bạn có chắc chắn muốn đăng ký không?");
            ButtonType buttonYes = new ButtonType("Đồng ý");
            ButtonType buttonCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonYes, buttonCancel);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonYes) {
                eventSelected.setQuantity(userName.getText());
            } else {
                alert.close();
            }
        } else {
            notification.setContentText("Bạn đã đăng ký sự kiện này rồi!");
        }
    }
}

