package com.example.betitarev.betitarev.objects;

import java.util.List;

//Singleton Must implement it
public class Bank {

    private List<Account> accounts;

    static Bank instance = null; // Singleton implementation.


    public Bank(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Bank(){}

    public Bank(Bank bank) {
        this.accounts = bank.accounts;
    }

    static public Bank getInstance(Bank bank) {
        if (instance != null)
            return instance;
        instance = new Bank(bank);
        return instance;
    }

    public void addAccount(Account account){
        this.accounts.add(account);
    }
    static public Bank getInstance() { return instance;    }

    public List<Account> getAccounts() {
        return accounts;
    }
}
