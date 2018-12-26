package com.example.betitarev.betitarev.objects;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class UsersNamesHashmap {

    static UsersNamesHashmap instance = null; // Singleton implementation.
    private HashMap<User, String> Hashmap = new HashMap<>();

    private UsersNamesHashmap(Set<User> Users) {
        for (User user : Users) {
            this.getHashmap().put(user, user.getName() + " " + user.getFamilyName());
        }
    }

    public static List<User> getAllKeysForValue(String value) {
        List<User> listOfKeys = null;
        HashMap<User, String> mapOfWords = UsersNamesHashmap.getInstance().getHashmap();
        if (mapOfWords.containsValue(value)) {
            listOfKeys = new ArrayList<>();
            for (Map.Entry<User, String> entry : mapOfWords.entrySet())
                if (entry.getValue().equals(value))
                    listOfKeys.add(entry.getKey());
        }
        return listOfKeys;
    }

    static public UsersNamesHashmap getInstance(Set<User> Users) {
        if (instance != null)
            return instance;
        instance = new UsersNamesHashmap(Users);
        return instance;
    }


    public static UsersNamesHashmap getInstance() {
        return instance;
    }

    public HashMap<User, String> getHashmap() {
        return Hashmap;
    }
}
