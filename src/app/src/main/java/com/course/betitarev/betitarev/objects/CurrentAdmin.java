package com.course.betitarev.betitarev.objects;


/**
 * This class represents the object Current Admin.
 */
public class CurrentAdmin extends BasicAdmin {

    static CurrentAdmin instance = null; // Singleton implementation.

    private CurrentAdmin(BasicAdmin ba, String userId) {
        super(ba, userId);
    }

    /**
     * Constructor.
     * @param ba
     * @param userId
     * @return
     */
    static public CurrentAdmin getInstance(BasicAdmin ba, String userId) {
        if (instance != null)
            return instance;
        instance = new CurrentAdmin(ba, userId);
        return instance;
    }

    public static CurrentAdmin getInstance() {
        return instance;
    }


}
