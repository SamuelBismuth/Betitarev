package com.example.betitarev.betitarev.objects;

public class Account {

    private User accountOwner;
    private FictiveMoney fictiveMoney;

    public Account(){}

    public Account(User accountOwner) {
        this.accountOwner = accountOwner;
        this.fictiveMoney = new FictiveMoney(50);  // Gift of 50$ at the beginning.
    }

    public User getAccountOwner() {
        return accountOwner;
    }

    public FictiveMoney getFictiveMoney() {
        return fictiveMoney;
    }
}
