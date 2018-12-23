package com.example.betitarev.betitarev.objects;

import android.net.Uri;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.example.betitarev.betitarev.libraries.FireBaseQuery.loadUserNameHashMap;

public class UsersNamesHashmap {




    private HashMap<Mail, String> Hashmap = new HashMap<>();
    static UsersNamesHashmap instance = null; // Singleton implementation.

    public UsersNamesHashmap(Set<Mail> Mails) {
        for (Mail mail : Mails) {
            loadUserNameHashMap(mail, this);
        }
    }
    public static List<Mail> getAllKeysForValue(String value)
    {
        List<Mail> listOfKeys = null;
        HashMap<Mail,String> mapOfWords = UsersNamesHashmap.getInstance().getHashmap();
        //Check if Map contains the given value
        if(mapOfWords.containsValue(value))
        {
            // Create an Empty List
            listOfKeys = new ArrayList<>();

            // Iterate over each entry of map using entrySet
            for (Map.Entry<Mail, String> entry : mapOfWords.entrySet())
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

    static public UsersNamesHashmap getInstance(Set<Mail> Mails) {
        if(instance != null)
            return instance;
        instance = new UsersNamesHashmap(Mails);
        return instance;
    }


    public static UsersNamesHashmap getInstance() {
        return instance;
    }

    public HashMap<Mail, String> getHashmap() {
        return Hashmap;
    }
}
