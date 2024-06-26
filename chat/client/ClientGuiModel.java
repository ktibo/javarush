package com.javarush.chat.client;

import java.util.Collections;
import java.util.TreeSet;
import java.util.Set;

public class ClientGuiModel {

    private final Set<String> allUserNames = new TreeSet();
    private String newMessage;

    public String getNewMessage() {
        return newMessage;
    }

    public void setNewMessage(String newMessage) {
        this.newMessage = newMessage;
    }

    public Set<String> getAllUserNames() {
        return Collections.unmodifiableSet(allUserNames);
    }

    public void addUser(String newUserName){
        allUserNames.add(newUserName);
    }

    public void deleteUser(String newUserName){
        allUserNames.remove(newUserName);
    }

}
