package com.example.betitarev.betitarev.objects;

public class CurrentAdmin extends BasicAdmin{


    static CurrentAdmin instance = null; // Singleton implementation.

    static public CurrentAdmin getInstance(BasicAdmin ba, String userId) {
        if(instance != null)
            return instance;
        instance = new CurrentAdmin(ba, userId);
        return instance;
    }
    private CurrentAdmin(BasicAdmin ba, String userId){super(ba, userId);}

    public static CurrentAdmin getInstance() {
        return instance;
    }


}
