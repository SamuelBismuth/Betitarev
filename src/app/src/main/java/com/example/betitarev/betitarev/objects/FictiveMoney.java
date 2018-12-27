package com.example.betitarev.betitarev.objects;

public class FictiveMoney {

    private int amount;

    public FictiveMoney(){}
    
    public FictiveMoney(int amount) {
        this.amount = amount;
    }

    protected boolean addMoney(int increase) {
        return false;
    }

    protected boolean loseMoney(int decrease) {
        return false;
    }

    public int getAmount() {
        return amount;
    }
}
