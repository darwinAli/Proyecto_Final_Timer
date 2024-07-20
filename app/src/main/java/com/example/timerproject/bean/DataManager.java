package com.example.timerproject.bean;

import com.example.timerproject.model.User;

import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private static DataManager instance;
    User currentUser;
    List<User> users=new ArrayList<>();

    private DataManager(){

    }

    public static synchronized DataManager getInstance(){
       if(instance==null){
           instance=new DataManager();
       }
       return  instance;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(User user) {
        this.users.add(user);
    }
}
