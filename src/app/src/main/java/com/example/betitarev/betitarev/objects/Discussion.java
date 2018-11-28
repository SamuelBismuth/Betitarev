package com.example.betitarev.betitarev.objects;

import java.util.List;

class Discussion {

    private List<Chat> chats;
    private User user1, user2;

    public Discussion(List<Chat> chats, User user1, User user2) {
        this.chats = chats;
        this.user1 = user1;
        this.user2 = user2;
    }

    protected int numberOfChats() {
        return 0;
    }

    protected boolean sendChat(Chat chat) {
        return false;
    }

    public List<Chat> getChats() {
        return chats;
    }

    public User getUser1() {
        return user1;
    }

    public User getUser2() {
        return user2;
    }
}
