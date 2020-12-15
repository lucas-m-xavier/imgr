/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.p2_pss.model;

import br.ufes.p2_pss.business.state.userstate.UserState;

/**
 *
 * @author Lucas
 */
public class User {
    
    private int id;
    
    private String username;
    
    private String password;
    
    private UserState userState;

    public User() {
    }

    public User(int id, String username, String password, UserState userState) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.userState = userState;
    }

    public User(String username, String password, UserState userState) {
        this.username = username;
        this.password = password;
        this.userState = userState;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public boolean validateUsernameAndPassword() {
        return (username.length() >= 5 && username.length() <= 20) && (password.length() >= 5 && password.length() <= 20);
    }
    
    public boolean login(String username, String password) {
        if(username.equals(this.username) ) {
            if(password.equals(this.password)) {
                return true;
            }
        }
        return false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserState getUserState() {
        return userState;
    }

    public void setUserState(UserState userState) {
        this.userState = userState;
    }
}
