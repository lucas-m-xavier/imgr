/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.p2_pss.business.memento;

/**
 *
 * @author Lucas
 */
public class ImageMemento {
    private int id;
    
    private String path;
    
    private String name;
    
    private boolean memento;

    public ImageMemento(int id, String path, String name, boolean memento) {
        this.id = id;
        this.path = path;
        this.name = name;
        this.memento = memento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMemento() {
        return memento;
    }

    public void setMemento(boolean memento) {
        this.memento = memento;
    }
    
    
}
