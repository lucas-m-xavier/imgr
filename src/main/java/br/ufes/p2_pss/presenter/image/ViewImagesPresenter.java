/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.p2_pss.presenter.image;

import br.ufes.p2_pss.presenter.requestaccess.RequestAcessPresenter;
import br.ufes.p2_pss.model.Image;
import br.ufes.p2_pss.model.Permission;
import br.ufes.p2_pss.model.User;
import br.ufes.p2_pss.presenter.home.HomePresenter;
import br.ufes.p2_pss.service.ImageService;
import br.ufes.p2_pss.service.PermissionService;
import br.ufes.p2_pss.util.ImageWithText;
import br.ufes.p2_pss.util.Renderer;
import br.ufes.p2_pss.view.Home;
import br.ufes.p2_pss.view.image.ViewImages;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Lucas
 */
public class ViewImagesPresenter {
    private ViewImages view;
    
    private final HomePresenter home;
    
    private final User user;
    
    DefaultListModel dlm = new DefaultListModel();

    public ViewImagesPresenter(HomePresenter home) throws Exception {
        this.home = home;
        this.view = new ViewImages();
        this.user = home.getUser();
        this.populate();
        
        this.view.getBtnClose().addActionListener((ActionEvent arg0) -> {
            this.view.dispose();
        });
        
        this.view.getBtnView().addActionListener((ActionEvent arg0) -> {
            try {
                this.viewImage();
            } catch (Exception ex) {
                Logger.getLogger(ViewImagesPresenter.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        this.view.getBtnDelete().addActionListener((ActionEvent arg0) -> {
            try {
                this.deleteImage();
            } catch (Exception ex) {
                Logger.getLogger(ViewImagesPresenter.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        this.view.getBtnDeleteAll().addActionListener((ActionEvent arg0) -> {
            try {
                this.deleteAllImages();
            } catch (Exception ex) {
                Logger.getLogger(ViewImagesPresenter.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        this.view.getBtnRequestAcess().addActionListener((ActionEvent arg0) -> {
            Object ob = this.view.getList().getSelectedValue();
            ImageWithText iwt = ((ImageWithText) ob);
            try {
                ImageService imageService = new ImageService();
                Image image = imageService.findImageBySource(iwt.getSource());
                
                RequestAcessPresenter a = new RequestAcessPresenter(image, user);
                Home.Desktop.add(a.getView());
                a.getView().setVisible(true);
            } catch (Exception ex) {
                Logger.getLogger(ViewImagesPresenter.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        this.view.setVisible(true);
    }
    
    private void populate() throws Exception {
        dlm.clear();
        
        ArrayList<Image> images = new ArrayList<>();
        ImageService imageService = new ImageService();
        images = imageService.getAll();
        
        for(Image image : images) {
            ImageIcon ii = new ImageIcon(image.getSource());
            ii.setImage(ii.getImage().getScaledInstance(200, 200, 1));
            
            dlm.addElement(new ImageWithText(image.getName(), ii, image.getSource()));
        }
        
        this.view.getList().setCellRenderer(new Renderer());
        this.view.getList().setModel(dlm);
    }
    
    private void viewImage() throws Exception {
        Object ob = this.view.getList().getSelectedValue();
        ImageWithText iwt = ((ImageWithText) ob);
        
        ImageService imageService = new ImageService();
        Image image = imageService.findImageBySource(iwt.getSource());
        
        PermissionService permissionService = new PermissionService();
        Permission permission = permissionService.findPermissionByImageAndUser(user, image);
        
        if (permission.isView()) {
            try {
                ViewImagePresenter a = new ViewImagePresenter(image);
                Home.Desktop.add(a.getView());
                a.getView().setVisible(true);
            } catch (Exception ex) {
                Logger.getLogger(HomePresenter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }   else {
            JOptionPane.showMessageDialog(null, "Você não tem permissão para visualizar esta imagem!");
        }
    }
    
    private void deleteImage() throws Exception {
        Object ob = this.view.getList().getSelectedValue();
        ImageWithText iwt = ((ImageWithText) ob);
        
        ImageService imageService = new ImageService();
        Image image = imageService.findImageBySource(iwt.getSource());
        
        PermissionService permissionService = new PermissionService();
        Permission permission = permissionService.findPermissionByImageAndUser(user, image);
        
        if (permission.isDelete()) {
            try {
                
                int response = JOptionPane.showConfirmDialog(view, "Tem certeza que deseja deletar a imagem " 
                        + image.getName()+ "?", "Excluir imagem", JOptionPane.YES_OPTION);
                
                if (response == JOptionPane.YES_OPTION) {
                    try {
                        
                        imageService = new ImageService();
                        imageService.delete(image.getId());
                        JOptionPane.showMessageDialog(null, "Imagem deletada com sucesso!");
                        
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex);
                    }
                }
                
            } catch (Exception ex) {
                Logger.getLogger(HomePresenter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }   else {
            JOptionPane.showMessageDialog(null, "Você não tem permissão para deletar esta imagem!");
        }
    }
    
    private void deleteAllImages() throws Exception {
        if(verifyPermissions()) {
            
            int response = JOptionPane.showConfirmDialog(view, "Tem certeza que deseja deletar todas as imagem?", 
                    "Excluir todas as imagens", JOptionPane.YES_OPTION);
                
                if (response == JOptionPane.YES_OPTION) {
                    try {
                        ImageService imageService = new ImageService();
                        
                        for(Image i : imageService.getAll()) {
                            imageService = new ImageService();
                            imageService.delete(i.getId());
                        }
                        
                        JOptionPane.showMessageDialog(null, "As imagens foram deletadas com sucesso!");
                        
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex);
                    }
                }
            
        }   else {
            JOptionPane.showMessageDialog(null, "Você não tem permissão para deletar todas as imagem!");
        }
    }
    
    private boolean verifyPermissions() throws Exception {
        boolean p = true;
        
        ImageService imageService = new ImageService();
        PermissionService permissionService;
        Permission permission;
        
        for(Image i : imageService.getAll()) {
            permissionService = new PermissionService();
            permission = permissionService.findPermissionByImageAndUser(user, i);
            if (!permission.isDelete()) return false;
        }
        return p;
    }

    public ViewImages getView() {
        return view;
    }
}
