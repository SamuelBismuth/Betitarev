package com.example.betitarev.betitarev.objects;

public class Statistics {

    private Statistic winStat, loseStat, drawStat, ArbitratorStat;

    public Statistics() {
        this.winStat = new Statistic(0);
        this.loseStat = new Statistic(0);
        this.drawStat = new Statistic(0);
        ArbitratorStat = new Statistic(0);
    }

    protected int getNumberOfGames(){
        return 0;
    }

    public Statistic getWinStat() {
        return winStat;
    }

    public Statistic getLoseStat() {
        return loseStat;
    }

    public Statistic getDrawStat() {
        return drawStat;
    }

    public Statistic getArbitratorStat() {
        return ArbitratorStat;
    }

}
