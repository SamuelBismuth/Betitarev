package com.example.betitarev.betitarev.objects;

public class CurrentAdmin extends BasicAdmin {


    static CurrentAdmin instance = null; // Singleton implementation.

    private CurrentAdmin(BasicAdmin ba, String userId) {
        super(ba, userId);
    }

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
