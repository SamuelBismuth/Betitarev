package com.example.betitarev.betitarev.objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class UsersNamesHashmap {




    private HashMap<User, String> Hashmap = new HashMap<>();
    static UsersNamesHashmap instance = null; // Singleton implementation.

    public UsersNamesHashmap(Set<User> Users) {
        for (User user : Users) {
            this.getHashmap().put(user, user.getCompleteName());        }
    }
    public static List<User> getAllKeysForValue(String value)
    {
        List<User> listOfKeys = null;
        HashMap<User,String> mapOfWords = UsersNamesHashmap.getInstance().getHashmap();
        //Check if Map contains the given value
        if(mapOfWords.containsValue(value))
        {
            // Create an Empty List
            listOfKeys = new ArrayList<>();

            // Iterate over each entry of map using entrySet
            for (Map.Entry<User, String> entry : mapOfWords.entrySet())
            {
                // Check if value matches with given value
                if (entry.getValue().equals(value))
                {
                    // Store the key from entry to the list
                    listOfKeys.add(entry.getKey());
                }
            }
        }
        // Return the list of keys whose value matches with given value.
        return listOfKeys;
    }

    static public UsersNamesHashmap getInstance(Set<User> Users) {
        if(instance != null)
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
