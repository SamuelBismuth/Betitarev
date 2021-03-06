package com.course.betitarev.betitarev.objects;

/**
 * This class represents the object Statistics.
 */
public class Statistics {

    private Statistic winStat, loseStat, drawStat, arbitratorStat;

    /**
     * Constructor.
     */
    public Statistics() {
        this.winStat = new Statistic(0);
        this.loseStat = new Statistic(0);
        this.drawStat = new Statistic(0);
        this.arbitratorStat = new Statistic(0);
    }

    /**
     * Constructor.
     *
     * @param winStat
     * @param loseStat
     * @param drawStat
     * @param arbitratorStat
     */
    public Statistics(Statistic winStat, Statistic loseStat, Statistic drawStat, Statistic arbitratorStat) {
        this.winStat = winStat;
        this.loseStat = loseStat;
        this.drawStat = drawStat;
        this.arbitratorStat = arbitratorStat;
    }

    protected int getNumberOfGames() {
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
        return arbitratorStat;
    }

}
