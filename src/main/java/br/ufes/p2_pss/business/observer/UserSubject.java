/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.p2_pss.business.observer;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Lucas
 */
public abstract class UserSubject {
    private final List<IUserObserver> observers = new ArrayList<>();

    public UserSubject() {
    }
    
    public void registerObserver(IUserObserver observer) {
        this.observers.add(observer);
    }
    
    public void unregisterObserver(IUserObserver observer) {
        this.observers.remove(observer);
    }
    
    public void notifyObserver() {
        observers.forEach(obs -> {
            obs.update();
        });
    }
}
