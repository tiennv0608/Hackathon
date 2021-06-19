package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.model.UserAccount;

import java.io.IOException;

public class ControllerDangNhap {
    public void logIn(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/sample.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        UserAccount userAccount = new UserAccount("123", "oa", "a", 1, "a", "a", "a");
        Controller controller = loader.getController();
        controller.setAccount(userAccount);
        stage.setScene(scene);
    }

}
