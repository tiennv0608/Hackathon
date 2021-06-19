package sample.controller;

import sample.model.UserAccount;

import java.util.ArrayList;
import java.util.List;

public class AccountManagement {
    List<UserAccount> accountUserList;

    public AccountManagement() {
        accountUserList = new ArrayList<>();
    }

    public List<UserAccount> getAccountUserList() {
        return accountUserList;
    }

    public void setAccountUserList(List<UserAccount> accountUserList) {
        this.accountUserList = accountUserList;
    }

    public UserAccount searchUser(String id) {
        for (UserAccount userAccount : accountUserList) {
            if (userAccount.getUserName().equals(id)) {
                return userAccount;
            }
        }
        return null;
    }
}