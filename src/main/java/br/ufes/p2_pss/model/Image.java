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
public class Image {
    
    private int id;
    
    private String source;
    
    private String name;

    public Image() {
    }

    public Image(int id, String source) {
        this.id = id;
        this.source = source;
    }

    public Image(String source) {
        this.source = source;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
