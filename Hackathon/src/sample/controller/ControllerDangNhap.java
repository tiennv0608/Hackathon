package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.model.UserAccount;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ControllerDangNhap {
    @FXML
    private TextField idUser;
    @FXML
    private TextField idPassword;
    @FXML
    private Label warning;
    AccountManagement accountManagement = new AccountManagement();


    public void logIn(ActionEvent e) throws IOException {

        accountManagement.getAccountUserList().add(new UserAccount("tien", "tien", "nvt", 10, "1", "1", "1"));
        accountManagement.getAccountUserList().add(new UserAccount("hung", "hung", "tqh", 9, "1", "1", "1"));
        accountManagement.getAccountUserList().add(new UserAccount("123", "son", "dhs", 8, "1", "1", "1"));
        accountManagement.getAccountUserList().add(new UserAccount("Username", "hieu", "nth", 10, "1", "1", "1"));
        if (checkID()) {
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/sample.fxml"));
            Parent sampleParent = loader.load();
            Scene scene = new Scene(sampleParent);
            UserAccount userAccount = accountManagement.searchUser(idUser.getText());
            Controller controller = loader.getController();
            controller.setAccount(userAccount);
            controller.getAll().addAll(IOEvent.readFromFile(IOEvent.PATH));
            stage.setScene(scene);
        } else {
            warning.setText("Sai id và password");
            idUser.setText("");
            idPassword.setText("");
        }
    }

    public boolean checkID() {
        for (UserAccount userAccount : accountManagement.getAccountUserList()) {
            if (idUser.getText().equals(userAccount.getUserName()) && idPassword.getText().equals(userAccount.getPassword())) {
                return true;
            }
        }
        return false;
    }
}
