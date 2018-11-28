package com.example.betitarev.betitarev.objects;

import java.util.List;

//Singleton
public class Bank {

    private List<Account> accounts;

    public Bank(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}
