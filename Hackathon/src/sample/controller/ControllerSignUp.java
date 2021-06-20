package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.model.UserAccount;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class ControllerSignUp {
    @FXML
    private Button createForm;
    @FXML
    private TextField nameForm;

    @FXML
    private PasswordField passForm;

    @FXML
    private PasswordField confirmPass;

    @FXML
    private TextField idForm;

    @FXML
    private TextField numberForm;

    @FXML
    private TextField addressForm;


    @FXML
    private TextField ageForm;


    @FXML
    private ComboBox<String> genderForm;

    @FXML
    private Button checkIdForm;


    List<UserAccount> userAccountList = new ArrayList<>();

    public void createAccount(ActionEvent event) {
        boolean checkInformation = true;
        UserAccount userAccount = new UserAccount();
        if (!checkIdForm()) {
            userAccount.setUserName(idForm.getText());
        } else {
            checkInformation = false;
        }
        userAccount.setAddress(addressForm.getText());
        userAccount.setGender(genderForm.getValue());
        userAccount.setPhoneNumber(numberForm.getText());
        userAccount.setAge(Integer.parseInt(ageForm.getText()));
        userAccount.setFullname(nameForm.getText());
        if (passForm.getText().equals(confirmPass.getText())) {
            userAccount.setPassword(passForm.getText());

        } else {
            checkInformation = false;
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Xác nhận mật khẩu không đúng!");
            alert.show();
        }
        if (checkInformation) {
            userAccountList.add(userAccount);
        }
        display();

    }

    public boolean checkIdForm() {
        for (UserAccount userAccount : userAccountList) {
            if (userAccount.getUserName().equals(idForm.getText())) {
                return true;
            }
        }
        return false;
    }

    public void alertCheckIdForm() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        if (checkIdForm()) {
            alert.setContentText("Tên đăng nhập đã tồn tại!");
            alert.show();
        } else {
            alert.setContentText("Tên đăng nhập hợp lệ!");
            alert.show();
        }
    }

    public void display() {
        for (UserAccount userAccount : userAccountList) {
            System.out.println(userAccount.toString());
        }
    }

}
