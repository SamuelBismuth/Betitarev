package com.example.betitarev.betitarev.objects;

public class Account {

    private User accountOwner;
    private FictiveMoney fictiveMoney;

    public Account(User accountOwner) {
        this.accountOwner = accountOwner;
        this.fictiveMoney = new FictiveMoney(50);  // Gift of 50$ at the beginning.
    }

}
