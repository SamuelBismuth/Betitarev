package com.course.betitarev.betitarev.objects;

/**
 * This class represents the object Statistics.
 */
public class Statistic {

    private int counter;

    /**
     * Constructor.
     */
    public Statistic() {
        this.counter = 0;
    }

    /**
     * Constructor.
     *
     * @param counter
     */
    public Statistic(int counter) {
        this.counter = counter;
    }

    protected void increase() {

    }

    protected boolean decrease() {
        return false;
    }

    public int getCounter() {
        return counter;
    }

}
