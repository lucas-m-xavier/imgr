/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.p2_pss.business.memento;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lucas
 */
public class JanitorImage {
    private List<ImageMemento> stack;
    
    public JanitorImage() {
        this.stack = new ArrayList<>();
    }
    
    public ImageMemento getLast(){
        if(stack.isEmpty()){
            return null;
        }
        
        int last = stack.size() - 1;
        return stack.remove(last);
    }
    
    public void addMemento(ImageMemento memento){
        this.stack.add(memento);
    }
    
    public boolean undo(){
        return !this.stack.isEmpty();
    }
}
