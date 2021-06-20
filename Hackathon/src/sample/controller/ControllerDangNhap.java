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
        accountManagement.getAccountUserList().addAll(IOUserAccount.readUser(IOUserAccount.PATH));
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

    public void signUp(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/signup.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
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
