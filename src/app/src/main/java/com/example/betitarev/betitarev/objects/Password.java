package com.example.betitarev.betitarev.objects;

public class Password {

    private String password;

    public Password(String password) {
        this.password = password;
    }

    protected boolean isStrongEnough() {
        return false;
    }

    private boolean includeNumber() {
        return false;
    }

    private boolean includeUpperCase() {
        return false;
    }

    private boolean longEnough() {
        return false;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
