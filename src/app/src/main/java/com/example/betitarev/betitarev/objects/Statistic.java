package com.example.betitarev.betitarev.objects;

public class Statistic {

    private int counter;

    public Statistic(){
        this.counter = 0;
    }

    public Statistic(int counter) {
        this.counter = counter;
    }

    protected void increase(){

    }

    protected boolean decrease(){
        return false;
    }

    public int getCounter() {
        return counter;
    }

}
