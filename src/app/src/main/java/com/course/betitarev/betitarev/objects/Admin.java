package com.course.betitarev.betitarev.objects;

/**
 * This class represents the object Admin which can delete account.
 */
public interface Admin {

    public boolean sendWarning(User player);

    public boolean removePlayer(User user);

}
