/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.p2_pss.model;

/**
 *
 * @author Lucas
 */
public class Notification {
    private int id;
    
    private String description;
    
    private User user;

    public Notification(int id, String description, User user) {
        this.id = id;
        this.description = description;
        this.user = user;
    }

    public Notification(String description, User user) {
        this.description = description;
        this.user = user;
    }

    public Notification() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
