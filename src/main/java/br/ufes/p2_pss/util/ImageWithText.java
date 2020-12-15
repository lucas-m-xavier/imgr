/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.p2_pss.util;

import javax.swing.Icon;

/**
 *
 * @author Lucas
 */
public class ImageWithText {
    
    private String name;
    
    private Icon icon;
    
    private String source;

    public ImageWithText(String name, Icon icon, String source) {
        this.name = name;
        this.icon = icon;
        this.source = source;
    }

    public ImageWithText() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
