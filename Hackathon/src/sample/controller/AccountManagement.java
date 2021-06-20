package sample.controller;

import sample.model.UserAccount;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AccountManagement implements Serializable {
    List<UserAccount> accountUserList;
    private static AccountManagement instance = new AccountManagement();
    public AccountManagement() {
        accountUserList = IOUserAccount.readUser(IOUserAccount.PATH);
    }

    public static AccountManagement getInstance() {
        return instance;
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