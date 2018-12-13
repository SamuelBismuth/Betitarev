package com.example.betitarev.betitarev.objects;

import java.util.List;

//Singleton Must implement it
public class Bank {

    private List<Account> accounts;

    public Bank(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}
