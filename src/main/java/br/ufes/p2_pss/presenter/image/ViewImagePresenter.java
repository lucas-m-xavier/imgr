/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.p2_pss.presenter.image;

import br.ufes.p2_pss.model.Image;
import br.ufes.p2_pss.view.image.ViewImage;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

/**
 *
 * @author Lucas
 */
public class ViewImagePresenter {
    
    private ViewImage view;

    public ViewImagePresenter(Image image) {
        
        this.view = new ViewImage();
        
        this.view.getBtnClose().addActionListener((ActionEvent arg0) -> {
            this.view.dispose();
        });
        
        this.view.getLblImage().setIcon(new ImageIcon(image.getSource()));
        
        this.view.setVisible(true);
        
    }

    public ViewImage getView() {
        return view;
    }
    
}
